package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Test {

	ServerSocket serverSocket=null;
	Socket clientSocket=null;
	BufferedReader inBufferedReader=null;
	PrintStream outPrintStream=null;
	
	
	public Test(int port){
		System.out.println("服务器代理正在监听端口:"+port);
		try {
			serverSocket=new ServerSocket(port);
		} catch (IOException e) {
			System.out.println("监听端口"+port+"失败");
		}
		
		try {
			clientSocket=serverSocket.accept();
		} catch (IOException e) {
			System.out.println("连接失败");
		}
		
		try {
			inBufferedReader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
			outPrintStream=new PrintStream(clientSocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public String getRequest(){
		String frmClt="1";
		try {
			frmClt=inBufferedReader.readLine();
			System.out.println("server 收到请求"+frmClt);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("无法读取端口...");
			System.exit(0);
		}
		return frmClt;
		
	}
	
	
	public void sendResponse(String response){
		try{
			outPrintStream.println(response);
			System.out.println("server 响应请求:"+response);
			outPrintStream.flush();
		}
		catch(Exception e){
			System.out.println("写端口失败。。。");
			System.exit(0);
		}
	}
	
	public void close(){
		try {
			inBufferedReader.close();
			outPrintStream.close();
		clientSocket.close();
		serverSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		Test test=new Test(8888);
		while(true){
			test.sendResponse(test.getRequest());
		    test.close();
		}
		}
//		// TODO Auto-generated method stub
//		InetAddress iAddress=null;
//		try {
//			iAddress=InetAddress.getLocalHost();
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(iAddress.getHostName());
//		System.out.println(iAddress.getHostAddress());
//		//输出本机ip地址和主机名
	

}
