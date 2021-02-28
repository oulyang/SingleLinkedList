package list2;

import java.util.Stack;

/**
 * 链表是节点的方式来存储数据
 * 每个节点包含data域和next域
 * 链表的各个节点不一定连续存储
 * 链表可以不带头节点
 * @author yyds
 *
 */

public class SingleLinkedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeroNode hn1=new HeroNode(1,"宋江","及时雨");
		HeroNode hn2=new HeroNode(2,"卢俊义","玉麒麟");
		HeroNode hn3=new HeroNode(3,"吴用","智多星");
		HeroNode hn4=new HeroNode(4,"林冲","豹子头");
		
		SingleLinkedList sll=new SingleLinkedList();
		
		HeroNode hn5=new HeroNode(3,"","");
		/*
		sll.add(hn1);
		sll.add(hn4);
		sll.add(hn3);
		sll.add(hn2);
		*/
		sll.addByOrder(hn3);
		sll.addByOrder(hn2);
		sll.addByOrder(hn4);
		sll.addByOrder(hn1);
		
		sll.modify(hn5);
		sll.remove(2);
		
		sll.showList();
		
		//反转链表测试
		//sll.reversetList(sll.getHead());
		//sll.showList();
		System.out.println(getLength(sll.getHead()));

		//逆序打印测试
		//sll.reversePrint(sll.getHead());
		
		
		//System.out.println(sll.getLength(sll.getHead()));
		
		//System.out.println(sll.findLastIndexNode(sll.getHead(),2));
		
		SingleLinkedList sll1=new SingleLinkedList();
		HeroNode hnn1=new HeroNode(1,"江","雨");
		HeroNode hnn2=new HeroNode(2,"俊义","麒麟");
		HeroNode hnn3=new HeroNode(3,"用","星");
		HeroNode hnn4=new HeroNode(4,"冲","头");
		hnn1.next=hnn3;
		hnn2.next=hnn4;
		HeroNode hn=mergeList(hnn1,hnn2);
		while(hn!=null) {
			System.out.println(hn);
			hn=hn.next;
		}
	}

	
	//题目：获取链表元素个数（若链表有头节点，不统计头节点）
	public static int getLength(HeroNode head) {
		int length=0;
		if(head.next==null) {
			return 0;
		}
		HeroNode cur=head.next;
		while(cur!=null) {
			length++;
			cur=cur.next;
		}
		return length;
	}
	
	//题目；查找单链表中倒数第k个节点
		//1.编写方法接收head节点及index（表示倒数第index个节点）
		//2.遍历链表，得到链表长度size
		//3.从链表第一个开始遍历size-index个
		//找到返回该节点，否则返回null
		public static HeroNode findLastIndexNode(HeroNode head,int index) {
			if(head.next==null) {
				return null;
			}
			//第一次遍历得到链表长度
			int size=getLength(head);
			//第二次遍历size-index位置，就是倒数第k个节点
			//验证index是否合法
			if(index<=0||index>size) {
				return null;
			}
			HeroNode cur=head.next;
			for(int i=0;i<size-index;i++) {
				cur=cur.next;
			}return cur;
		}
		//题目：节点反转
		/*1.定义一个节点reverseHead=new HeroNde()
		 * 2.遍历原来的链表，将得到的每一个节点取出，放在新链表的最前端
		 * 3.原来的链表的head.next=reverseHead.next
		 * 
		 */
		public static void reversetList(HeroNode head) {
			//若链表为空或只有一个元素
			if(head.next==null||head.next.next==null) {
				return;
			}
			//定义以一个变量来遍历原来的链表
			HeroNode cur=head.next;
			//指向当前节点的下一个节点，用来移出数据
			HeroNode next=null;
			
			HeroNode reverseHead=new HeroNode(0,"","");
			//遍历原来的链表
			while(cur!=null) {
				//暂时保存当前节点的下一个节点
				next=cur.next;
				//将当前节点cur的下一个节点指向新的链表最前端
				cur.next=reverseHead.next;
				
				reverseHead.next=cur;
				//让cur后移
				cur=next;
			}
			//将head.next指向reverseHead。next，实现单链表反转
			head.next=reverseHead.next;
		}
		
		//题目：逆序打印单链表
		//方式1.反转链表再遍历（不建议，回破坏原链表结构）
		//方式2.利用栈的先进后出
		public static void reversePrint(HeroNode head) {
			//空链表
			if(head.next==null) {
				return;
			}
			Stack<HeroNode> stack=new Stack<>();
			HeroNode cur=head.next;
			//压栈
			while(cur!=null) {
				stack.push(cur);
				cur=cur.next;
			}
			//遍历栈中节点
			while(stack.size()>0) {
				System.out.println(stack.pop());
			}
		}

		
		
		
		//合并两个有序链表
		
		public static HeroNode mergeList(HeroNode head1,HeroNode head2) {
			if(head1.next==null||head2.next==null) {
				return head1 !=null?head1:head2;
			}
			
			HeroNode head=head1.no<head2.no?head1:head2;
			HeroNode cur1=head==head1?head1:head2;
			HeroNode cur2=head==head1?head2:head1;
			
			HeroNode pre =null;//cur1前一个元素
			HeroNode next=null;//cur2后一个元素
			
			while(cur1!=null&&cur2!=null) {
				if(cur1.no<=cur2.no) {
					pre=cur1;
					cur1=cur1.next;
				}else {
					next=cur2.next;
					pre.next=cur2;
					cur2.next=cur1;
					pre=cur2;
					cur2=next;
				}
			}
			pre.next=cur1==null?cur2:cur1;
			return head;
		}
}

class SingleLinkedList{
	//初始化头节点
	private HeroNode head=new HeroNode(0,"","");
	public HeroNode getHead() {
		return head;
	}
	//往单向链表末尾添加数据
	//1.找到末尾节点 2.末尾节点的next指向添加元素heroNode
	public void add(HeroNode heroNode) {
		HeroNode temp=head;
		while(true) {
			if(temp.next==null){
				break;
			}
			temp=temp.next;
		}
		//退出循环，temp指向链表最后
		temp.next=heroNode;
	}

	//有序添加节点
	public void addByOrder(HeroNode heroNode) {
		HeroNode temp=head;
		boolean flag=false;
		while(true) {
			if(temp.next==null) {
				break;
			}
			if(temp.next.no>heroNode.no) {
				break;
			}else if(temp.next.no==heroNode.no) {
				flag=true;
				break;
			}
			temp=temp.next;
		}
		if(flag) {
			//不添加
			System.out.println("准备插入的元素已存在");
		}else {
			heroNode.next=temp.next;
			temp.next=heroNode;
		}
	}
	
	//修改节点
	public void modify(HeroNode heroNode) {
		if(head.next==null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp=head;
		boolean flag=false;
		while(true) {
			if(temp==null) {//已经遍历完链表
				break;
			}
			if(temp.no==heroNode.no) {
				flag=true;
				break;
			}
			temp=temp.next;
		}
		if(flag) {
			temp.name=heroNode.name;
			temp.nickName=heroNode.nickName;
		}else {
			System.out.println("为找到编号为"+heroNode.no+"的元素");
		}
	}
	
	//删除编号为no的节点
	public void remove(int no) {
		/*
		 * if(head.next==null) { System.out.println("链表为空，无法删除"); }
		 */
		HeroNode temp=head;
		boolean flag=false;
		while(true) {
			if(temp.next==null) {
				System.out.println("找不到待删除元素");
				break;
			}
			if(temp.next.no==no) {
				flag=true;
				break;
			}
			temp=temp.next;
		}
		if(flag) {
			temp.next=temp.next.next;
		}else {
			System.out.println("不存在要删除的元素");
		}
	}
		
	//显示链表
	public void showList() {
		if(head.next==null) {
			System.out.println("链表为空");
			return;
		}
		HeroNode temp=head.next;
		while(true) {
			if(temp==null) {
				break;
			}
			//输出信息
			System.out.println(temp);
			//后移temp
			temp=temp.next;
		}
	}
	
}

class HeroNode {
	int no;
	String name;
	String nickName;
	HeroNode next;
	public HeroNode() {
		super();
	}
	public HeroNode(int no, String name, String nickName) {
		super();
		this.no = no;
		this.name = name;
		this.nickName = nickName;
	}
	public String toString() {
		return "["+no+","+name+","+nickName+"]";
	}
}