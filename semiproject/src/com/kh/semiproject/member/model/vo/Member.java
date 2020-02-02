package com.kh.semiproject.member.model.vo;

import java.sql.Date;

public class Member {
	private String memberId;
	private String memberName;
	private String memberPwd;
	private String memberEmail;
	private String memberPhone;
	private String memberWebTell;
	private String memberCommentTell;
	private String memberAskTell;
	private String memberRTTell;
	private String memberEmailCertify;
	private String memberStatus;
	public Member(String memberId, String memberName, String memberPhone, String memberProImg) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPhone = memberPhone;
		this.memberProImg = memberProImg;
	}

	public Member(String memberId, String memberName, String memberEmail, String memberPhone, String memberProImg) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberProImg = memberProImg;
	}

	private String memberGrade;
	private Date memberSignUpDT;
	private String memberProImg;
	
	public Member() {}

	public Member(String memberId, String memberName, String memberPwd, String memberEmail, String memberPhone,
			String memberWebTell, String memberCommentTell, String memberAskTell, String memberRTTell,
			String memberEmailCertify, String memberStatus, String memberGrade, Date memberSignUpDT,
			String memberProImg) {
		this(memberId, memberName, memberEmail, memberPhone, memberWebTell, memberCommentTell, memberAskTell, memberRTTell, memberEmailCertify, memberStatus, memberGrade, memberSignUpDT, memberProImg);
		this.memberPwd = memberPwd;
	}
	
	
	

	/** 관리자페이지 회원목록 조회용 
	 * @param memberId
	 * @param memberName
	 * @param memberPwd
	 */
	@SuppressWarnings("unchecked")
	public Member(String memberId, String memberName, String memberEmail) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
	}


	public Member(String memberId, String memberName, String memberEmail, String memberPhone, String memberWebTell,
			String memberCommentTell, String memberAskTell, String memberRTTell, String memberEmailCertify,
			String memberStatus, String memberGrade, Date memberSignUpDT, String memberProImg) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberWebTell = memberWebTell;
		this.memberCommentTell = memberCommentTell;
		this.memberAskTell = memberAskTell;
		this.memberRTTell = memberRTTell;
		this.memberEmailCertify = memberEmailCertify;
		this.memberStatus = memberStatus;
		this.memberGrade = memberGrade;
		this.memberSignUpDT = memberSignUpDT;
		this.memberProImg = memberProImg;
	}
	
	


	

	public Member(String memberId, String memberPwd) {
		super();
		this.memberId = memberId;
		this.memberPwd = memberPwd;
	}
	
	
	
	
	

	public Member(String memberId, String memberName, String memberPwd, String memberEmail, String memberPhone,
			String memberProImg) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.memberPwd = memberPwd;
		this.memberPhone = memberPhone;
		this.memberEmail = memberEmail;
		this.memberProImg = memberProImg;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberPwd() {
		return memberPwd;
	}

	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public String getMemberWebTell() {
		return memberWebTell;
	}

	public void setMemberWebTell(String memberWebTell) {
		this.memberWebTell = memberWebTell;
	}

	public String getmemberCommentTell() {
		return memberCommentTell;
	}

	public void setmemberCommentTell(String memberCommentTell) {
		this.memberCommentTell = memberCommentTell;
	}

	public String getMemberAskTell() {
		return memberAskTell;
	}

	public void setMemberAskTell(String memberAskTell) {
		this.memberAskTell = memberAskTell;
	}

	public String getMemberRTTell() {
		return memberRTTell;
	}

	public void setMemberRTTell(String memberRTTell) {
		this.memberRTTell = memberRTTell;
	}

	public String getMemberEmailCertify() {
		return memberEmailCertify;
	}

	public void setMemberEmailCertify(String memberEmailCertify) {
		this.memberEmailCertify = memberEmailCertify;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String getMemberGrade() {
		return memberGrade;
	}

	public void setMemberGrade(String memberGrade) {
		this.memberGrade = memberGrade;
	}

	public Date getMemberSignUpDT() {
		return memberSignUpDT;
	}

	public void setMemberSignUpDT(Date memberSignUpDT) {
		this.memberSignUpDT = memberSignUpDT;
	}

	public String getMemberProImg() {
		return memberProImg;
	}

	public void setMemberProImg(String memberProImg) {
		this.memberProImg = memberProImg;
	}

	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", memberName=" + memberName + ", memberPwd=" + memberPwd
				+ ", memberEmail=" + memberEmail + ", memberPhone=" + memberPhone + ", memberWebTell=" + memberWebTell
				+ ", memberCommentTell=" + memberCommentTell + ", memberAskTell=" + memberAskTell + ", memberRTTell="
				+ memberRTTell + ", memberEmailCertify=" + memberEmailCertify + ", memberStatus=" + memberStatus
				+ ", memberGrade=" + memberGrade + ", memberSignUpDT=" + memberSignUpDT + ", memberProImg="
				+ memberProImg + "]";
	}
	
	
	
	
	
	
}
