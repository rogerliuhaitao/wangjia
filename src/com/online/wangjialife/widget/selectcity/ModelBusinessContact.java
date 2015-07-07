package com.online.wangjialife.widget.selectcity;

import com.online.wangjialife.R;
import com.online.wangjialife.util.UtilRegex;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;



/**
 * 企业联系人
 * @author Administrator
 *
 */
public class ModelBusinessContact extends IndicatorModelData{
	
	private String id;//用户编号
	private String username;//用户登录名称
	private String password;//密码
//	private boolean enabled;//是否过期    false
	private String packageId;//套餐id
	private String packageName;//套餐名称       --  "A套餐"
	private String expiredTime;//到期时间   --  1439308800000    
	private String name;//姓名
	private String alphabetize;//联系人全拼
	private String janespell;//拼音简拼  （联系人姓名拼音首字母）
	private String sex;//性别     （0：女，1：男）
	private String brithday;//生日   --649436400000
//	private String mobile;//手机号 码
	private String email;//邮箱
	private String companyId;//公司id
	private String companyName;//公司名称
	private String departmentId;//部门id
	private String dmName;//部门名称
	private String roleId;//角色id
	private String roleName;//角色名称
	private String position;//职务
	private String createDate;//创建时间
	private String jobNumber;//工号
	private String headimg="http://www.logo33.com/upload/editor/2013/0803/20130802fgsdfgs00958%281%29.jpg";//头像
	private String remark;//备注
//	private int state;//有效无效标识(1有效，0无效)

	@Override
	public String getKeyName() {
		String alphabet = alphabetize!=null?alphabetize.substring(0,1).toUpperCase():"";
		if (!TextUtils.isEmpty(alphabet)&&UtilRegex.isAlphabet(alphabet)) {
			return alphabet;
		}else {
//			return "#";
			return alphabetize;
		}
	}
	
	@Override
	public View getView(final Context pContext) { 
		View convertView = LayoutInflater.from(pContext).inflate(R.layout.item_indicator_business_contacts_content_item, null, false);
		/**头像*/
		ImageViewWidget mImageViewWidget_HeadProtrait = (ImageViewWidget) convertView.findViewById(R.id.ItemBusinessContactsListContent_HeadProtrait);
		mImageViewWidget_HeadProtrait.setResNone(R.drawable.icon_avator_none);
		mImageViewWidget_HeadProtrait.setImage(getHeadimg());
		/**联系人名字*/
		TextView mTextView_Name = (TextView) convertView.findViewById(R.id.ItemBusinessContactsListContent_ContactName);
		mTextView_Name.setText(name==null?"":name);
		return convertView;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

//	public boolean isEnabled() {
//		return enabled;
//	}
//
//	public void setEnabled(boolean enabled) {
//		this.enabled = enabled;
//	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(String expiredTime) {
		this.expiredTime = expiredTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlphabetize() {
		return alphabetize;
	}

	public void setAlphabetize(String alphabetize) {
		this.alphabetize = alphabetize;
	}

	public String getJanespell() {
		return janespell;
	}

	public void setJanespell(String janespell) {
		this.janespell = janespell;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBrithday() {
		return brithday;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

//	public String getMobile() {
//		return mobile;
//	}
//
//	public void setMobile(String mobile) {
//		this.mobile = mobile;
//	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getDmName() {
		return dmName;
	}

	public void setDmName(String dmName) {
		this.dmName = dmName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getHeadimg() {
		return headimg;
	}

	public void setHeadimg(String headimg) {
		this.headimg = headimg;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
//	public int getState() {
//		return state;
//	}
//
//	public void setState(int state) {
//		this.state = state;
//	}

//	public ModelBusinessContact(String id,String mobile, String username, String name,
//			String alphabetize, String janespell,String dmName) {
//		super();
//		this.id = id;
//		this.mobile = mobile;
//		this.username = username;
//		this.name = name;
//		this.alphabetize = alphabetize;
//		this.janespell = janespell;
//		this.dmName = dmName;
//	}

	//这个事按字母排列的构造方法
	/**
	 * 
	 * @param id 编号
	 * @param name 名称
	 * @param alphabetize 拼音的全拼或者是“热门城市”、“定位城市”
	 */
	public ModelBusinessContact(String id,String name,String alphabetize) {
		super();
		this.id = id;
		this.name = name;
		this.alphabetize = alphabetize;
//		this.mobile = mobile;
	}
	public ModelBusinessContact(String id,String name) {
		super();
		this.id = id;
		this.name = name;
//		this.mobile = mobile;
	}

	
	
	
}
