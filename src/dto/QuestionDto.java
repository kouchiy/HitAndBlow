package dto;

/**
 * クエスチョンの情報を格納するDto
 */
public class QuestionDto {

	/** クエスチョン */
	private String question;

	/**
	 * クエスチョンを取得します。
	 * @return クエスチョン
	 */
	public String getQuestion() {
	    return question;
	}

	/**
	 * クエスチョンを設定します。
	 * @param question クエスチョン
	 */
	public void setQuestion(String question) {
	    this.question = question;
	}
}
