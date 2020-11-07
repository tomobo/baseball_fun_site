package models.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import models.UserProfile;
import utils.DBUtil;

public class UserProfileValidator {
    public static List<String> validateProfile(UserProfile ep, Boolean profile_image_duplicate_check) {
        List<String> errors = new ArrayList<String>();

        String profile_image_errors = _validateProfileImage(ep.getProfile_image(), profile_image_duplicate_check);
        //System.out.println(profile_image_errors);
        if(!profile_image_errors.equals("")) {
            errors.add(profile_image_errors);
        }
        //System.out.println(errors);
        return errors;
    }

    // ユーザーID
    /*第2引数にBoolean型の引数を用意し、そこが true であればバリデーションチェックを実行する
     * 変更処理(update)の時に、入力チェックが不要な場合も存在するため。*/
    private static String _validateProfileImage(String profile_image, Boolean profile_image_duplicate_check) {
        // 必須入力チェック
        if(profile_image == null || profile_image.equals("")) {
            return "プロフィール画像を入力してください。";
        }

        // すでに登録されているファイル名との重複チェック
        if(profile_image_duplicate_check) {
            EntityManager em = DBUtil.createEntityManager();
            long users_count = (long)em.createNamedQuery("checkRegisteredProfileImage", Long.class)
                                           .setParameter("profile_image", profile_image)
                                             .getSingleResult();
            em.close();
            //System.out.println(users_count);
            if(users_count > 0) {
                return "同一のファイル名で既に登録があります。ファイル名を変更してください。";
            }
        }
        return "";
    }
}
