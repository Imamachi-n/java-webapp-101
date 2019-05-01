package validator;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseValidator {

	// エラー有無
	private boolean hasError;

	// エラーメッセージ
	private ArrayList<String> errorMessage;

	// 情報メッセージ
	private ArrayList<String> infoMessage;

	// コンストラクタ
	public BaseValidator() {
		this.errorMessage = new ArrayList<String>();
		this.infoMessage = new ArrayList<String>();
	}

	// 桁数チェック
	public boolean checkByte(String str, int maxBytes, String errorMsg) throws UnsupportedEncodingException {

		// Nullチェック
		if(str == null || str.length() == 0) {
			this.getErrorMessage().add(errorMsg);
			this.setHasError(true);
			return false;
		}

		// 桁数チェック
		if(str.getBytes("Shift-JIS").length <= maxBytes) {
			// バイト数が最大値以下の場合、Trueを返す
			return true;

		}else {
			// バイト数が最大値を超える場合、Falseを返す
			this.getErrorMessage().add(errorMsg);
			this.setHasError(true);
			return false;

		}
	}

	// 数値チェック
	public boolean isNumber(String str, String errorMsg) {

		try {
			// 数値の場合
	        Integer.parseInt(str);
	        return true;

	    } catch (NumberFormatException e) {
	    	// 数値でない場合
	    	this.getErrorMessage().add(errorMsg);
			this.setHasError(true);
	        return false;

	    }
	}
}
