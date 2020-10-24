package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.UserProfile;
import utils.DBUtil;

public class UserProfileValidator {
    public static List<String> validateProfile(UserProfile e, Boolean code_duplicate_check_flag, Boolean password_check_flag) {
        List<String> errors = new ArrayList<String>();

        String profile_image = _validateProfileImage(e.getProfile_image(), code_duplicate_check_flag);
        if(!profile_image.equals("")) {
            errors.add(profile_image);
        }
        return errors;
    }

    // ユーザーID
    /*第2引数にBoolean型の引数を用意し、そこが true であればバリデーションチェックを実行する
     * 変更処理(update)の時に、入力チェックが不要な場合も存在するため。*/
    private static String _validateProfileImage(String profile_image, Boolean code_duplicate_check_flag) {
        // 必須入力チェック
        if(profile_image == null || profile_image.equals("")) {
            return "プロフィール画像を入力してください。";
        }

        // すでに登録されているファイル名との重複チェック
        if(code_duplicate_check_flag) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredCode", Long.class)
                                           .setParameter("profile_image", profile_image)
                                             .getSingleResult();
            em.close();
            if(users_count > 0) {
                return "同一のファイル名で既に登録があります。ファイル名を変更してください。";
            }
        }
        return "";
    }
}
