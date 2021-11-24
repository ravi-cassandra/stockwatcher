package org.stockwatcher.domain;

public enum WatchListRating {
	UNKNOWN, POOR, FAIR, GOOD, VERY_GOOD, EXCELLENT;

	public static WatchListRating getRating(float value) {
		return value > 15.0f ? EXCELLENT :
			value > 10.0f ? VERY_GOOD :
				value > 5.0f ? GOOD :
					value > 0.0f ? FAIR : POOR;
	}

	@Override
	public String toString() {
		return name().replace("_", " ");
	}
}