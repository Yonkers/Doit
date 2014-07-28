/*
 * File:PassClickResultVisitor.java
 * Date:2014-3-10下午3:47:26
 *
 * 四川长虹网络科技有限责任公司 (智能应用研发部)© 版权所有 
 */
package com.yong.doit.pdf;


/**
 * @author yonkers
 */
public abstract class PassClickResultVisitor {
	public abstract void visitText(PassClickResultText result);
	public abstract void visitChoice(PassClickResultChoice result);
}
