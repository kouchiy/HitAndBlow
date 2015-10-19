package dto;

/**
 * 
 * HitAndBlowの結果を格納するクラス
 * 
 * @author kaneshirok
 *
 */
public class HabResultDto {
	
	/** HitAndBlowの結果文字列 */
	private String habResultStr;

	/**
	 * HitAndBlowの結果文字列を取得します。
	 * @return HitAndBlowの結果文字列
	 */
	public String getHabResultStr() {
	    return habResultStr;
	}

	/**
	 * HitAndBlowの結果文字列を設定します。
	 * @param habResultStr HitAndBlowの結果文字列
	 */
	public void setHabResultStr(String habResultStr) {
	    this.habResultStr = habResultStr;
	}
}
