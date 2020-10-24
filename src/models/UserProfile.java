package models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Table(name = "users_profile")
//4つのselect文を作成
@NamedQueries({
    //全てのユーザ情報を取得
    @NamedQuery(
            name = "getUsera",
            query = "SELECT e FROM User AS e ORDER BY e.id DESC"
            ),
    //ユーザ情報数を取得(削除済みのユーザーは含めない)
    @NamedQuery(
            name = "getUsersCounta",
            query = "SELECT COUNT(e) FROM User AS e WHERE e.delete_flag = 0"
            ),
    //指定されたユーザーIDがデータベースに存在しているか調べる
    @NamedQuery(
            name = "checkRegisteredCodea",
            query = "SELECT COUNT(e) FROM User AS e WHERE e.bbid = :user_id"
            ),
    //ユーザーがログインする時にユーザーIDとパスワードが正しいかチェックする
    @NamedQuery(
            name = "checkLoginCodeAndPassworda",
            query = "SELECT e FROM User AS e WHERE e.delete_flag = 0 AND e.bbid = :user_id AND e.password = :pass"
            ),
   //指定されたユーザーIDとメールアドレスが一致するIDを取得する
   @NamedQuery(
            name = "checkIdentificationGetIda",
            query = "SELECT e.id FROM User AS e WHERE e.bbid = :user_id AND e.mail_address = :mail_address"
            )
   //指定されたIDのパスワードを更新する
   //@NamedQuery(
   //         name = "updatePassword",
   //         query = "UPDATE User SET password = :password where id = :id"
   //         ),

})

@Entity(name = "UserProfile")
public class UserProfile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "bbid") // 付けなくてもOK
    private User user;

    @Column(name = "profile", nullable = false)
    private String profile;

    @Column(name = "profile_image", nullable = false)
    private String profile_image;

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

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
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

