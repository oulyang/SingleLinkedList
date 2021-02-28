package list2;

import java.util.Stack;

/**
 * �����ǽڵ�ķ�ʽ���洢����
 * ÿ���ڵ����data���next��
 * ����ĸ����ڵ㲻һ�������洢
 * ������Բ���ͷ�ڵ�
 * @author yyds
 *
 */

public class SingleLinkedListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeroNode hn1=new HeroNode(1,"�ν�","��ʱ��");
		HeroNode hn2=new HeroNode(2,"¬����","������");
		HeroNode hn3=new HeroNode(3,"����","�Ƕ���");
		HeroNode hn4=new HeroNode(4,"�ֳ�","����ͷ");
		
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
		
		//��ת�������
		//sll.reversetList(sll.getHead());
		//sll.showList();
		System.out.println(getLength(sll.getHead()));

		//�����ӡ����
		//sll.reversePrint(sll.getHead());
		
		
		//System.out.println(sll.getLength(sll.getHead()));
		
		//System.out.println(sll.findLastIndexNode(sll.getHead(),2));
		
		SingleLinkedList sll1=new SingleLinkedList();
		HeroNode hnn1=new HeroNode(1,"��","��");
		HeroNode hnn2=new HeroNode(2,"����","����");
		HeroNode hnn3=new HeroNode(3,"��","��");
		HeroNode hnn4=new HeroNode(4,"��","ͷ");
		hnn1.next=hnn3;
		hnn2.next=hnn4;
		HeroNode hn=mergeList(hnn1,hnn2);
		while(hn!=null) {
			System.out.println(hn);
			hn=hn.next;
		}
	}

	
	//��Ŀ����ȡ����Ԫ�ظ�������������ͷ�ڵ㣬��ͳ��ͷ�ڵ㣩
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
	
	//��Ŀ�����ҵ������е�����k���ڵ�
		//1.��д��������head�ڵ㼰index����ʾ������index���ڵ㣩
		//2.���������õ�������size
		//3.�������һ����ʼ����size-index��
		//�ҵ����ظýڵ㣬���򷵻�null
		public static HeroNode findLastIndexNode(HeroNode head,int index) {
			if(head.next==null) {
				return null;
			}
			//��һ�α����õ�������
			int size=getLength(head);
			//�ڶ��α���size-indexλ�ã����ǵ�����k���ڵ�
			//��֤index�Ƿ�Ϸ�
			if(index<=0||index>size) {
				return null;
			}
			HeroNode cur=head.next;
			for(int i=0;i<size-index;i++) {
				cur=cur.next;
			}return cur;
		}
		//��Ŀ���ڵ㷴ת
		/*1.����һ���ڵ�reverseHead=new HeroNde()
		 * 2.����ԭ�����������õ���ÿһ���ڵ�ȡ�����������������ǰ��
		 * 3.ԭ���������head.next=reverseHead.next
		 * 
		 */
		public static void reversetList(HeroNode head) {
			//������Ϊ�ջ�ֻ��һ��Ԫ��
			if(head.next==null||head.next.next==null) {
				return;
			}
			//������һ������������ԭ��������
			HeroNode cur=head.next;
			//ָ��ǰ�ڵ����һ���ڵ㣬�����Ƴ�����
			HeroNode next=null;
			
			HeroNode reverseHead=new HeroNode(0,"","");
			//����ԭ��������
			while(cur!=null) {
				//��ʱ���浱ǰ�ڵ����һ���ڵ�
				next=cur.next;
				//����ǰ�ڵ�cur����һ���ڵ�ָ���µ�������ǰ��
				cur.next=reverseHead.next;
				
				reverseHead.next=cur;
				//��cur����
				cur=next;
			}
			//��head.nextָ��reverseHead��next��ʵ�ֵ�����ת
			head.next=reverseHead.next;
		}
		
		//��Ŀ�������ӡ������
		//��ʽ1.��ת�����ٱ����������飬���ƻ�ԭ����ṹ��
		//��ʽ2.����ջ���Ƚ����
		public static void reversePrint(HeroNode head) {
			//������
			if(head.next==null) {
				return;
			}
			Stack<HeroNode> stack=new Stack<>();
			HeroNode cur=head.next;
			//ѹջ
			while(cur!=null) {
				stack.push(cur);
				cur=cur.next;
			}
			//����ջ�нڵ�
			while(stack.size()>0) {
				System.out.println(stack.pop());
			}
		}

		
		
		
		//�ϲ�������������
		
		public static HeroNode mergeList(HeroNode head1,HeroNode head2) {
			if(head1.next==null||head2.next==null) {
				return head1 !=null?head1:head2;
			}
			
			HeroNode head=head1.no<head2.no?head1:head2;
			HeroNode cur1=head==head1?head1:head2;
			HeroNode cur2=head==head1?head2:head1;
			
			HeroNode pre =null;//cur1ǰһ��Ԫ��
			HeroNode next=null;//cur2��һ��Ԫ��
			
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
	//��ʼ��ͷ�ڵ�
	private HeroNode head=new HeroNode(0,"","");
	public HeroNode getHead() {
		return head;
	}
	//����������ĩβ�������
	//1.�ҵ�ĩβ�ڵ� 2.ĩβ�ڵ��nextָ�����Ԫ��heroNode
	public void add(HeroNode heroNode) {
		HeroNode temp=head;
		while(true) {
			if(temp.next==null){
				break;
			}
			temp=temp.next;
		}
		//�˳�ѭ����tempָ���������
		temp.next=heroNode;
	}

	//������ӽڵ�
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
			//�����
			System.out.println("׼�������Ԫ���Ѵ���");
		}else {
			heroNode.next=temp.next;
			temp.next=heroNode;
		}
	}
	
	//�޸Ľڵ�
	public void modify(HeroNode heroNode) {
		if(head.next==null) {
			System.out.println("����Ϊ��");
			return;
		}
		HeroNode temp=head;
		boolean flag=false;
		while(true) {
			if(temp==null) {//�Ѿ�����������
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
			System.out.println("Ϊ�ҵ����Ϊ"+heroNode.no+"��Ԫ��");
		}
	}
	
	//ɾ�����Ϊno�Ľڵ�
	public void remove(int no) {
		/*
		 * if(head.next==null) { System.out.println("����Ϊ�գ��޷�ɾ��"); }
		 */
		HeroNode temp=head;
		boolean flag=false;
		while(true) {
			if(temp.next==null) {
				System.out.println("�Ҳ�����ɾ��Ԫ��");
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
			System.out.println("������Ҫɾ����Ԫ��");
		}
	}
		
	//��ʾ����
	public void showList() {
		if(head.next==null) {
			System.out.println("����Ϊ��");
			return;
		}
		HeroNode temp=head.next;
		while(true) {
			if(temp==null) {
				break;
			}
			//�����Ϣ
			System.out.println(temp);
			//����temp
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