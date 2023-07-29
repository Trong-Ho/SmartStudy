/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.smart.models;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */
@Entity
@Table(name = "ScheduleDetail")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScheduleDetail.findAll", query = "SELECT s FROM ScheduleDetail s"),
    @NamedQuery(name = "ScheduleDetail.findById", query = "SELECT s FROM ScheduleDetail s WHERE s.id = :id"),
    @NamedQuery(name = "ScheduleDetail.findByDate", query = "SELECT s FROM ScheduleDetail s WHERE s.date = :date"),
    @NamedQuery(name = "ScheduleDetail.findBySlot", query = "SELECT s FROM ScheduleDetail s WHERE s.slot = :slot"),
    @NamedQuery(name = "ScheduleDetail.findByDateOfWeek", query = "SELECT s FROM ScheduleDetail s WHERE s.dateOfWeek = :dateOfWeek"),
    @NamedQuery(name = "ScheduleDetail.findByTeacherId", query = "SELECT s FROM ScheduleDetail s WHERE s.teacherId = :teacherId")})
public class ScheduleDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "slot")
    private Integer slot;
    @Size(max = 100)
    @Column(name = "date_of_week")
    private String dateOfWeek;
    @Column(name = "teacher_id")
    private Integer teacherId;
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    @ManyToOne
    private Schedule scheduleId;
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @ManyToOne
    private Subject subjectId;

    public ScheduleDetail() {
    }

    public ScheduleDetail(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getSlot() {
        return slot;
    }

    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    public String getDateOfWeek() {
        return dateOfWeek;
    }

    public void setDateOfWeek(String dateOfWeek) {
        this.dateOfWeek = dateOfWeek;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Schedule getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Schedule scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Subject getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Subject subjectId) {
        this.subjectId = subjectId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScheduleDetail)) {
            return false;
        }
        ScheduleDetail other = (ScheduleDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.example.smart.models.ScheduleDetail[ id=" + id + " ]";
    }
    
}
