package com.poscodx.payload;

public class Analysis {

	private String uuid;
    private Float tps;
    private Integer total_count;
    private Integer success_count;
    private Integer failure_count;
    private Float avg_time;
    private String timestamp;
    
    public Analysis() {
    }
    
	public Analysis(String uuid, Float tps, Integer total_count, Integer success_count,
			Integer failure_count, Float avg_time, String timestamp) {
		super();
		this.uuid = uuid;
		this.tps = tps;
		this.total_count = total_count;
		this.success_count = success_count;
		this.failure_count = failure_count;
		this.avg_time = avg_time;
		this.timestamp = timestamp;
	}

	public String getUuid() {
		return uuid;
	}
	public Float getTps() {
		return tps;
	}
	public Integer getTotal_count() {
		return total_count;
	}
	public Integer getSuccess_count() {
		return success_count;
	}
	public Integer getFailure_count() {
		return failure_count;
	}
	public Float getAvg_time() {
		return avg_time;
	}
	public String getTimestamp() {
		return timestamp;
	}

	@Override
	public String toString() {
		return "Analysis [uuid=" + uuid + ", tps=" + tps + ", total_count=" + total_count
				+ ", success_count=" + success_count + ", failure_count=" + failure_count + ", avg_time=" + avg_time
				+ ", timestamp=" + timestamp + "]";
	}

}
