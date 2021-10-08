package com.route.utils;

public class AcaUtils
{
	static int antnum = 200;//��������
	static int alpha = 1;//������
	static int beta = 4;//������
	static double rho = 0.9;//��Ϣ�ػӷ�����
	
	//����Ϣ�صĳ�ʼ�����и���
	private double[][] getp(double distance[][],int citynum)
	{
		//������Ϣ����
		double p[][] = new double[citynum][citynum];
		for(int i=0;i<citynum;i++) 
		{
			double sum = sumeachcity(distance, i,citynum);
			for(int j=0;j<citynum;j++) 
			{
				p[i][j]=(distance[i][j])/sum;
			}
		}
		return p;
	}
	
	//�߳�һ��·��
	private int[] getpath(double p[][],double distance[][],int citynum)
	{
		int cityover[] = new int[citynum];//�����߹��ĳ��м�·��
		for(int m=0;m<citynum;m++) 
		{
			cityover[m]=100;//��ʼֵ��Ϊ100,��ʾ��δ�߹�
		}
		int temple = (int) (Math.random()*citynum);//����һ��0��citynum֮����������
		cityover[0]=temple;//���ȡ�ó�ʼλ��
		int current =temple;//��¼��ǰλ��
		for(int i=1;i<citynum;i++)
		{
			int next = roulette(distance,p,cityover,current,citynum);
			cityover[i]=next;
			current = next;
		}
		return cityover;
	}
	
	//���̶ľ�����һ������
	private int roulette(double distance[][],double p[][],int cityover[],int current,int citynum)
	{
		double possible[]=new double[citynum];
		double sum = 0;
		int temple = -1;
		double a = denominator(distance, p,cityover,current,citynum);
		for(int i=0;i<citynum;i++)
		{
			if(canget(i,cityover,citynum))//�ó���δȥ��
			{
				double t = p[current][i];
				t = Math.pow(t, alpha);
				double n =1.0/distance[current][i];
				n = Math.pow(n, beta);
				possible[i] = n*t/a;
			}
			else//�ó�����ȥ��
			{
				possible[i]=0;
			}
		}
		double pointer = Math.random();//�������ת��ָ��
		for(int j=0;sum<pointer;j++)//ģ��ת��ת��
		{
			sum += possible[j];
			temple++;
		}
		return temple;	
	}
	
	//�жϵ�ǰ�����Ƿ��ȥ
	private boolean canget(int current,int cityover[],int citynum)
	{
		for(int i=0;i<citynum;i++) 
		{
			if(current == cityover[i]) return false;
		}
		return true;
	}
	
	
	//���������߹�·���ܳ�
	private double sumlength(double distance[][],int path[][],int current,int citynum)
	{
		double sum=0;
		for(int i=0;i<citynum-1;i++) 
		{
			sum += distance[path[current][i]][path[current][i+1]];
		}
		sum += distance[path[current][citynum-1]][path[current][0]];
		return sum;
	}
	
	//�����i�����е�������о���֮��
	public double sumeachcity(double distance[][],int i,int citynum)
	{
		double sum=0;
		for(int j = 0;j<citynum;j++) 
		{
			sum += distance[i][j];
		}
		return sum;
	}
	
	//����ӵ�ǰ�㵽���������ʹ�ʽ�ķ�ĸֵ
	private double denominator(double distance[][],double p[][],int cityover[],int current,int citynum)
	{
		double sum=0;
		for(int i=0;i<citynum;i++) 
		{
			if(canget(i, cityover,citynum)) 
			{
				double a =p[current][i];
				a= Math.pow(a, alpha);
				double b =1.0/distance[current][i];
				b= Math.pow(b,beta);
				sum += a*b;
			}
		}
		return sum;
	}
		
	public static int[] aca(double distance[][],int citynum)
	{
		AcaUtils suanfa =new AcaUtils();//ʵ����
		double p[][] =suanfa.getp(distance,citynum);//������Ϣ����
		double p2[][]=new double[citynum][citynum];//p2���Ա�֤ÿ�ε���ʱ����֮�䲻�໥Ӱ�� 
		for(int i=0;i<citynum;i++)
		{
			for(int j=0;j<citynum;j++)
			{
				p2[i][j]=p[i][j];
			}
		}
		int path[][] = new int[antnum][citynum];//����ÿֻ�����ߵ�·��
		double min = Integer.MAX_VALUE;//�������·��
		int bestpath[]=new int[citynum];//�������·��
		for(int i=0;i<100;i++) //����100��
		{
			for(int j=0;j<antnum;j++)//��ÿ������ȥѰ��·�߲��ͷ���Ϣ��
			{
				int cityover[] = suanfa.getpath(p2,distance,citynum);//����һ��·��
				for(int m=0;m<citynum;m++)//����·�߱�����·��������
				{
					path[j][m]=cityover[m];
				}
				double length = suanfa.sumlength(distance, path, j,citynum);//�������·�߳���
				if(length<min)
				{
					min = length;//�������Ž�
					for(int k=0;k<citynum;k++)//�������·��
					{
						bestpath[k]=path[j][k];
					}
			
				}
				for(int k=0;k<citynum-1;k++)
				{
					//�ͷ���Ϣ��
					p[path[j][k]][path[j][k+1]] += 1.0/length;
					p[path[j][k+1]][path[j][k]]=p[path[j][k]][path[j][k+1]];
				}
				p[path[j][citynum-1]][path[j][0]] += 1.0/length;
				p[path[j][0]][path[j][citynum-1]]=p[path[j][citynum-1]][path[j][0]];
			}
			//�ӷ���Ϣ��
			for(int j=0;j<citynum;j++)
			{
				for(int k=0;k<citynum;k++)
				{
					p[j][k]=rho*p[j][k];
				}
			}
			for(int j=0;j<citynum;j++)
			{
				for(int k=0;k<citynum;k++)
				{
					p2[j][k]=p[j][k];
				}
			}
		}
		//���
		System.out.println("结果为：");
		for(int i=0;i<citynum;i++)
		{
			System.out.println(bestpath[i]+1);
		}
		System.out.println("最短距离为");
		System.out.println(min);
		return bestpath;
	}
}