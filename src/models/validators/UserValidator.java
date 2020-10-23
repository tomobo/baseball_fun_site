package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.User;
import utils.DBUtil;

public class UserValidator {
    public static List<String> validate(User e, Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String bbid_error = _validateBbid(e.getBbid(), code_duplicate_check_flag);
        if(!bbid_error.equals("")) {
            errors.add(bbid_error);
        }

        String user_name_error = _validateUser_name(e.getUser_name());
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
            long users_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("bbid", bbid)
                                             .getSingleResult();
            em.close();
            if(users_count > 0) {
                return "入力されたユーザーIDはすでに存在しています。";
            }
        }

        return "";
    }

    // ユーザー名の必須入力チェック
    private static String _validateUser_name(String user_name) {
        if(user_name == null || user_name.equals("")) {
            return "ユーザー名を入力してください。";
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
