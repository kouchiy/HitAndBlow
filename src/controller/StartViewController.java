package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * スタート画面からのリクエストに対しての処理を行うコントローラー
 * プレイヤーが作成したプログラムを呼び出し、3ストライクになった時点で、結果画面に遷移する
 */
@WebServlet("/StartViewController")
public class StartViewController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * コンストラクタ
     */
    public StartViewController() {
        super();
    }

	/**
	 * Getメソッド
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * Postメソッド
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
