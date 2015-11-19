/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mss.msp.usr.task;

import java.util.Map;

/**
 *
 * @author miracle
 */
public class TasksVTO {

    private String task_id;
    private String task_name;
    private String task_comments;
    private String task_created_by;
    private String task_status;
    private String task_created_date;
    private String from_date;
    private String to_date;
    private String pri_assigned_to;
    private String sec_assigned_to;
    private String task_relatedto_name;
    /*added by praveen<pkatru@miraclesoft.com>
     * Date:4/20/2015
     * For Use: retrive data from data base for task Edit
     */
    private String task_related_to;
    private String task_type;
    private Map task_typeMap;
    private String task_priority;
    private String task_title;
    private String task_description;
    private String task_alert;
    private String task_alert_me;
    private String alert_msg;
    private String start_date;
    private String end_date;
    private Map typeMaps;
    private Map primaryMaps;
    private String attachmentId;
    private String attachmentName;
    private String attachmentPath;
    private int sec_reportsId;
    private int task_prj_related_to;
    private String relatedProject;
    private String taskPriority;
    private String createdDate;

    public String getTask_id() {
        return task_id;
    }

    public void setTask_id(String task_id) {
        this.task_id = task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public String getTask_comments() {
        return task_comments;
    }

    public void setTask_comments(String task_comments) {
        this.task_comments = task_comments;
    }

    public String getTask_created_by() {
        return task_created_by;
    }

    public void setTask_created_by(String task_created_by) {
        this.task_created_by = task_created_by;
    }

    public String getTask_status() {
        return task_status;
    }

    public void setTask_status(String task_status) {
        this.task_status = task_status;
    }

    public String getTask_created_date() {
        return task_created_date;
    }

    public void setTask_created_date(String task_created_date) {
        this.task_created_date = task_created_date;
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getPri_assigned_to() {
        return pri_assigned_to;
    }

    public void setPri_assigned_to(String pri_assigned_to) {
        this.pri_assigned_to = pri_assigned_to;
    }

    public String getSec_assigned_to() {
        return sec_assigned_to;
    }

    public void setSec_assigned_to(String sec_assigned_to) {
        this.sec_assigned_to = sec_assigned_to;
    }

    public String getTask_relatedto_name() {
        return task_relatedto_name;
    }

    public void setTask_relatedto_name(String task_relatedto_name) {
        this.task_relatedto_name = task_relatedto_name;
    }

    public String getTask_related_to() {
        return task_related_to;
    }

    public void setTask_related_to(String task_related_to) {
        this.task_related_to = task_related_to;
    }

    public String getTask_type() {
        return task_type;
    }

    public void setTask_type(String task_type) {
        this.task_type = task_type;
    }

    public Map getTask_typeMap() {
        return task_typeMap;
    }

    public void setTask_typeMap(Map task_typeMap) {
        this.task_typeMap = task_typeMap;
    }

    public String getTask_priority() {
        return task_priority;
    }

    public void setTask_priority(String task_priority) {
        this.task_priority = task_priority;
    }

    public String getTask_title() {
        return task_title;
    }

    public void setTask_title(String task_title) {
        this.task_title = task_title;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }

    public String getTask_alert() {
        return task_alert;
    }

    public void setTask_alert(String task_alert) {
        this.task_alert = task_alert;
    }

    public String getTask_alert_me() {
        return task_alert_me;
    }

    public void setTask_alert_me(String task_alert_me) {
        this.task_alert_me = task_alert_me;
    }

    public String getAlert_msg() {
        return alert_msg;
    }

    public void setAlert_msg(String alert_msg) {
        this.alert_msg = alert_msg;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public Map getTypeMaps() {
        return typeMaps;
    }

    public void setTypeMaps(Map typeMaps) {
        this.typeMaps = typeMaps;
    }

    public Map getPrimaryMaps() {
        return primaryMaps;
    }

    public void setPrimaryMaps(Map primaryMaps) {
        this.primaryMaps = primaryMaps;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentName() {
        return attachmentName;
    }

    public void setAttachmentName(String attachmentName) {
        this.attachmentName = attachmentName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public int getSec_reportsId() {
        return sec_reportsId;
    }

    public void setSec_reportsId(int sec_reportsId) {
        this.sec_reportsId = sec_reportsId;
    }

    public int getTask_prj_related_to() {
        return task_prj_related_to;
    }

    public void setTask_prj_related_to(int task_prj_related_to) {
        this.task_prj_related_to = task_prj_related_to;
    }

    public String getRelatedProject() {
        return relatedProject;
    }

    public void setRelatedProject(String relatedProject) {
        this.relatedProject = relatedProject;
    }

    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(String taskPriority) {
        this.taskPriority = taskPriority;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

}
