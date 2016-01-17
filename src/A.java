import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class A extends JFrame{

	private JButton bt;
	private JLabel counting,task;
	
	
	public A(){
				
		setLayout(new FlowLayout());
		
		counting = new JLabel("0");
		task = new JLabel("Task Not Completed");

		bt = new JButton("Click");

		
		bt.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				Start();
				
			}
		});
		
		
		add(counting);
		add(bt);
		add(task);
		
		setSize(607,400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	private void Start(){
		
		
		
		SwingWorker<Boolean,Integer> worker = new SwingWorker<Boolean,Integer>(){

			@Override
			protected Boolean doInBackground() throws Exception {


				for(int i=0;i<30;i++){
					//1000 Milliseconds = 1 second
					Thread.sleep(100);
					System.out.println("Hello: " +i);
		//publish method aik zariya hai jo data ya element ko process method k paas behjtha hai					publish(i);
					publish(i);
				}
				
				return true;
			}

		

			@Override
			protected void process(List<Integer> chunks) {

			
			Integer value = chunks.get(chunks.size() - 1);
			
			counting.setText("Current Value: "+value);
			}

			@Override
			protected void done() {
	
				
				try {
					Boolean status = get();
					task.setText("Completed with status : "+ status);
					
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
				
			}
			


			
		};
		
     //Now it's working on it's own thread and GUI is responsive
		worker.execute();
	}
	
	
}
