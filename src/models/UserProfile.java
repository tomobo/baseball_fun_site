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

@Table(name = "users_profile")
//4つのselect文を作成
@NamedQueries({
  //指定されたprofile_imageがデータベースに存在しているか調べる
   @NamedQuery(
            name = "checkRegisteredProfileImage",
            query = "SELECT COUNT(e) FROM UserProfile AS e WHERE e.profile_image = :profile_image"
            ),
  //指定されたユーザーIDのオブジェクトを取得する
    @NamedQuery(
            name = "getEntity",
            query = "SELECT e FROM UserProfile AS e WHERE e.user_id = :user_id"
            )

})

@Entity(name = "UserProfile")
public class UserProfile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(name = "bbid", nullable = false) // 付けなくてもOK
    //private String bbid;

    @Column(name = "user_id", nullable = false)
    private Integer user_id;

    @Column(name = "bbid", nullable = false)
    private String bbid;

    //@Column(name = "profile", nullable = true)
    //private String profile;

    @Column(name = "profile_image", nullable = true)
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

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getBbid() {
        return bbid;
    }

    public void setBbid(String bbid) {
        this.bbid = bbid;
    }

    //public String getProfile() {
    //    return profile;
    //}

    //public void setProfile(String profile) {
    //    this.profile = profile;
    //}

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

