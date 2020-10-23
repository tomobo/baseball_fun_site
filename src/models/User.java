package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "users")
//4つのseect文を作成
@NamedQueries({
    //全てのユーザ情報を取得
    @NamedQuery(
            name = "getAllUsers",
            query = "SELECT e FROM User AS e ORDER BY e.id DESC"
            ),
    //ユーザ情報数を取得(削除済みのユーザーは含めない)
    @NamedQuery(
            name = "getUsersCount",
            query = "SELECT COUNT(e) FROM User AS e WHERE e.delete_flag = 0"
            ),
    //指定されたユーザーIDがデータベースに存在しているか調べる
    @NamedQuery(
            name = "checkRegisteredCode",
            query = "SELECT COUNT(e) FROM User AS e WHERE e.bbid = :bbid"
            ),
    //ユーザーがログインする時にユーザーIDとパスワードが正しいかチェックする
    @NamedQuery(
            name = "checkLoginCodeAndPassword",
            query = "SELECT e FROM User AS e WHERE e.delete_flag = 0 AND e.bbid = :bbid AND e.password = :pass"
            ),
   //指定されたユーザーIDとメールアドレスが一致するIDを取得する
   @NamedQuery(
            name = "checkIdentificationGetId",
            query = "SELECT e.id FROM User AS e WHERE e.bbid = :bbid AND e.mail_address = :mail_address"
            )
   //指定されたIDのパスワードを更新する
   //@NamedQuery(
   //         name = "updatePassword",
   //         query = "UPDATE User SET password = :password where id = :id"
   //         ),

})

@Entity(name = "User")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //user_idのフィールドにはunique制約を追加
    @Column(name = "bbid", nullable = false, unique = true)
    private String bbid;

    @Column(name = "user_name", nullable = false)
    private String user_name;

    //@Column(name = "profile", nullable = false)
    //private String profile;

    //@Column(name = "profile_image", nullable = false)
    //private String profile_image;

    @Column(name = "mail_address", nullable = false)
    private String mail_address;

    /*入力できる文字情報が最大64文字まで。SHA256というハッシュ関数でパスワードをハッシュ化するため
     * SHA256 は、どんな文字数の文字列でも必ず、64文字のハッシュ化された文字列にする*/
    @Column(name = "password", length = 64, nullable = false)
    private String password;

    //@Column(name = "admin_flag", nullable = false)
    //private Integer admin_flag;

    @Column(name = "created_at", nullable = false)
    private Timestamp created_at;

    @Column(name = "updated_at", nullable = false)
    private Timestamp updated_at;

    @Column(name = "delete_flag", nullable = false)
    private Integer delete_flag;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBbid() {
        return bbid;
    }

    public void setBbid(String bbid) {
        this.bbid = bbid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    //public String getProfile() {
    //    return profile;
    //}

    //public void setProfile(String profile) {
    //    this.profile = profile;
    //}

    //public String getProfile_image() {
    //    return profile_image;
    //}

    //public void setProfile_image(String profile_image) {
    //    this.profile_image = profile_image;
    //}

    public String getMail_address() {
        return mail_address;
    }

    public void setMail_address(String mail_address) {
        this.mail_address = mail_address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }

    public Timestamp getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Timestamp updated_at) {
        this.updated_at = updated_at;
    }

    public Integer getDelete_flag() {
        return delete_flag;
    }

    public void setDelete_flag(Integer delete_flag) {
        this.delete_flag = delete_flag;
    }
}
