package new_Word.搜索文件;

import java.io.File;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	private Scanner in;
	public static void main(String []args){
		new BlockingQueueTest().serch_File();
	}
	public void serch_File(){
		final int File_QUEUe_SIZE =10;
		final int SEARCH_THREADS=100;
		in = new Scanner(System.in);
		System.out.println("Enter base directory(e.g /D:/一些PDF)");
		String directory=in.nextLine();
		System.out.println("(e.g java)");
		String keyword=in.nextLine();
		BlockingQueue<File> queue =new ArrayBlockingQueue<File>(File_QUEUe_SIZE);
		FileEnumerationTask en=new FileEnumerationTask(queue, new File(directory));
		Thread t=new Thread(en);
		t.start();
		for(int i=1;i<=SEARCH_THREADS;i++){
			new Thread(new SearchTask(queue, keyword)).start();
		}
	}
}
