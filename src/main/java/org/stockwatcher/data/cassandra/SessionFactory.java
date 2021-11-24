package org.stockwatcher.data.cassandra;

import static java.util.concurrent.TimeUnit.SECONDS;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Component;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Cluster.Builder;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Session;

/**
 * Factory class that creates instances of the Session class. class requires an
 * existing keyspace and at least one node to access the cluster. 
 *  
 * This is sample code, provided as-is, and we make no warranties as to the 
 * correctness or suitability for any purpose.
 * 
 * @author Tony Piazza (tpiazza@datastax.com)
 *
 * Copyright DataStax, Inc.
 */
@Component
public class SessionFactory {
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionFactory.class);
	private static final long SHUTDOWN_TIMEOUT = 10;

	private String[] nodes;
	private String keyspace;
	private Cluster cluster;
	private Session session;

	@Required
	public void setNodes(String[] nodes) {
		this.nodes = nodes;
	}

	@Required
	public void setKeyspace(String keyspace) {
		this.keyspace = keyspace;
	}

	@PostConstruct
	public void startup() {
		if(cluster == null) {
			if(nodes == null || nodes.length == 0) {
				throw new IllegalStateException("no nodes specified");
			}
			Builder builder = Cluster.builder();
			builder.addContactPoints(nodes);
			cluster = builder.build();
			session = cluster.connect(keyspace);
			for(Host host : getAllHosts()) {
				LOGGER.info("Cluster node: {}", host);
			}
		}
	}

	@PreDestroy
	public void shutdown() {
		if(cluster != null) {
			try {
				// Close asynchronously, then get with our timeout value
				// get will throw exception if timeout exceeded, or close fails 
				cluster.closeAsync().get(SHUTDOWN_TIMEOUT, SECONDS);
			} catch (Exception e) {
				LOGGER.error("Unable to shutdown cluster", e);
			}
		}
	}

	public String getKeyspace() {
		return keyspace;
	}

	public Set<Host> getAllHosts() {
		return cluster.getMetadata().getAllHosts();
	}

	public Session getSession() {
		return session;
	}
}