package com.demo.compass.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserEntity implements Serializable {
    @SerializedName("UserID")
    @Expose
    private int userId;

    @SerializedName("Name")
    @Expose
    private String name;

    @SerializedName("ProgramID")
    @Expose
    private int programId;

    @SerializedName("ProgramName")
    @Expose
    private String programName;

    @SerializedName("ProjectID")
    @Expose
    private int projectId;
    @SerializedName("TypeProgram")
    @Expose
    private String typeProgram;

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getProjectId() {
        return projectId;
    }

    public String getTypeProgram() {
        return typeProgram;
    }



    public int getProgramId() {
        return programId;
    }

    public String getProgramName() {
        return programName;
    }
}