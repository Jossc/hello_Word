package new_Word.搜索文件;

import java.io.File;
import java.util.concurrent.BlockingQueue;

public class FileEnumerationTask implements Runnable{
	private BlockingQueue<File> queue;
	private File Stat;
	public static File dummy=new File("");
	public FileEnumerationTask(BlockingQueue<File> queue,File Stat) {
		this.queue=queue;
		this.Stat=Stat;
	}
	@Override
	public void run() {
		try {
			enumerate(Stat);
			queue.put(dummy);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 遍历文件目录
	 * @param directory
	 * @throws InterruptedException
	 */
	public void enumerate(File directory) throws InterruptedException{
		File[]files=directory.listFiles();
		for(File file:files){
			if(file.isDirectory()){
				enumerate(file);
				System.out.println(file.getPath());
			}
			else 
				queue.put(file);
		}
	}

}
