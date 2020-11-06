package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    //バリデーションチェック。ブラウザ側(javaScript)で実装する方法とサーバー側に(java)で実装する方法がある。
    //JavaScriptができないので、サーバー側で作成。
    public static List<String> validate(User e, Boolean code_duplicate_check_flag, Boolean user_name_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String bbid_error = _validateBbid(e.getBbid(), code_duplicate_check_flag);
        if(!bbid_error.equals("")) {
            errors.add(bbid_error);
        }

        String user_name_error = _validateUser_name(e.getUser_name(), user_name_duplicate_check_flag);
        if(!user_name_error.equals("")) {
            errors.add(user_name_error);
        }

        String password_error = _validatePassword(e.getPassword(), password_check_flag);
        if(!password_error.equals("")) {
            errors.add(password_error);
        }

        return errors;
    }

    // ユーザーID
    /*第2引数にBoolean型の引数を用意し、そこが true であればバリデーションチェックを実行する
     * 変更処理(update)の時に、入力チェックが不要な場合も存在するため。*/
    private static String _validateBbid(String bbid, Boolean code_duplicate_check_flag) {
        // 必須入力チェック
        if(bbid == null || bbid.equals("")) {
            return "ベースボールIDを入力してください。";
        }

        // すでに登録されているユーザーIDとの重複チェック
        if(code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long bbid_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("bbid", bbid)
                                             .getSingleResult();
            em.close();
            if(bbid_count > 0) {
                return "入力されたユーザーIDはすでに存在しています。";
            }
        }

        return "";
    }

    // ユーザーネームの必須入力/重複チェック
    private static String _validateUser_name(String user_name, Boolean user_name_duplicate_check_flag) {
        // 必須入力チェック
        if(user_name == null || user_name.equals("")) {
            return "ユーザー名を入力してください。";
        }
        // すでに登録されているユーザーIDとの重複チェック
        if(user_name_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long user_name_count = (long)em.createNamedQuery("checkRegisteredUserName", Long.class)
                                           .setParameter("user_name", user_name)
                                             .getSingleResult();
            em.close();
            if(user_name_count > 0) {
                return "入力されたユーザーネームはすでに存在しています。";
            }
        }

        return "";
    }

    // パスワードの必須入力チェック
    /*第2引数にBoolean型の引数を用意し、そこが true であればバリデーションチェックを実行する
     * 変更処理(update)の時に、入力チェックが不要な場合も存在するため。*/
    private static String _validatePassword(String password, Boolean password_check_flag) {
        // パスワードを変更する場合のみ実行
        if(password_check_flag && (password == null || password.equals(""))) {
            return "パスワードを入力してください。";
        }
        return "";
    }
}
