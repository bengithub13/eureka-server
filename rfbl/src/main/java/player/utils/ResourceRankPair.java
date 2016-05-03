package player.utils;

public class ResourceRankPair {

	private String resourceUrl;
	private int rank;
	
	public ResourceRankPair(String resourceUrl, int rank){
		this.resourceUrl = resourceUrl;
		this.rank = rank;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

}
