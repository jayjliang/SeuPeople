package internet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;

import static java.lang.System.out;

public class Client {
	final String print="System.out.println";
	PrintStream out;
	BufferedReader inBufferedReader;
	public Client(String servername,int port) {
		try {
			Socket clientSocket=new Socket(servername,port);
			out=new PrintStream(clientSocket.getOutputStream());
			inBufferedReader=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		
		} catch (UnknownHostException e) {
			System.out.println("地址错误");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("kehu连接失败");
			System.exit(0);
		}
	}
	
	
	public void sendRequest(String response){
		try{
			out.println(response);
			System.out.println("client 发送请求:"+response);
		}
		catch(Exception e){
		}
	}
	
	public String getResponse(){
		String string=new String();
		try {
			string=inBufferedReader.readLine();
			System.out.println("Client收到server返回:"+string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return string;
	}
	public static void main(String[] args) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Client client=new Client("221.226.174.164",8888);
				while(true){
		client.sendRequest("你好");
		client.getResponse();
				}
			}
		}).start();
		
	}

}
