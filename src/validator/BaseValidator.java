package validator;

import java.io.UnsupportedEncodingException;

public class BaseValidator {

	// エラー有無
	private boolean hasError;

	// エラーメッセージ
	private String errorMessage;

	// 桁数チェック
	public boolean checkByte(String str, int maxBytes) throws UnsupportedEncodingException {

		// Nullチェック
		if(str == null || str.length() == 0) {
			return false;
		}

		// 桁数チェック
		if(str.getBytes("Shift-JIS").length <= maxBytes) {
			// バイト数が最大値以下の場合、Trueを返す
			return true;

		}else {
			// バイト数が最大値を超える場合、Falseを返す
			return false;

		}
	}

	// 数値チェック
	public boolean isNumber(String str) {

		try {
			// 数値の場合
	        Integer.parseInt(str);
	        return true;

	    } catch (NumberFormatException e) {
	    	// 数値でない場合
	        return false;

	    }
	}
}
