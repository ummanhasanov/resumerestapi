package com.company.dto;
import com.company.entity.User;
import com.company.entity.UserSkill;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDTO {
    private int id;
    private String name;
    private String surname;
    private String password;
    private String email;
    private String phone;
    private String profileDesc;
    private String address;

    private Date birthdate;
    private List<UserSkillDTO> skills;
    public UserDTO() {
    }
    public UserDTO(User u){
        this.id = u.getId();
        this.name = u.getName();
        this.surname = u.getSurname();
//        this.password = u.getPassword();

        //  sonra duzelis edecem

        this.email = u.getEmail();
//        this.phone = u.getPhone();
//        this.profileDesc = u.getProfileDesc();
//        this.address = u.getAddress();
//        this.birthdate = u.getBirthdate();

        List<UserSkillDTO> list = new ArrayList<>();

        List<UserSkill> userSkills = u.getUserSkillList();
        for (int i = 0; i< userSkills.size(); i++){
            UserSkill uSkill = userSkills.get(i);
            list.add(new UserSkillDTO(uSkill));
        }
        skills = list;
    }

    public UserDTO(int id, String name, String surname,String email) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<UserSkillDTO> getSkills() {
        return skills;
    }

    public void setSkills(List<UserSkillDTO> skills) {
        this.skills = skills;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    //  sonra duzelis edecem
//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }
//
//    public String getProfileDesc() {
//        return profileDesc;
//    }
//
//    public void setProfileDesc(String profileDesc) {
//        this.profileDesc = profileDesc;
//    }
//
//    public String getAddress() {
//        return address;
//    }
//
//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Date getBirthdate() {
//        return birthdate;
//    }
//
//    public void setBirthdate(Date birthdate) {
//        this.birthdate = birthdate;
//    }
}
