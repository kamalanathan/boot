package com.zk;

import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.x.async.AsyncCuratorFramework;
import static org.awaitility.Awaitility.await;

public class ConMngmnt2 {
	public static void chckCon() throws Exception {
		int sleepMsBetweenRetries = 100;
		int maxRetries = 3;
		RetryPolicy retryPolicy = new RetryNTimes(maxRetries, sleepMsBetweenRetries);

		CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
		client.start();

		System.out.println(client.checkExists().forPath("/"));
		client.close();
	}

	public static void leaderSelector() throws Exception {
		int sleepMsBetweenRetries = 100;
		int maxRetries = 3;
		RetryPolicy retryPolicy = new RetryNTimes(maxRetries, sleepMsBetweenRetries);

		CuratorFramework client = CuratorFrameworkFactory.newClient("master:2181", retryPolicy);
		client.start();

		LeaderLatch leaderLatch = new LeaderLatch(client, "/kamal");
		leaderLatch.addListener(new LeaderLatchListener() {

			@Override
			public void notLeader() {
				// TODO Auto-generated method stub

			}

			@Override
			public void isLeader() {
				System.out.println("Leader1");

			}
		});
		leaderLatch.start();

		// LeaderSelector leaderSelector = new LeaderSelector(client,
		// "/mutex/select/leader/for/job/A",
		// new LeaderSelectorListener() {
		// @Override
		// public void stateChanged(CuratorFramework client, ConnectionState newState) {
		// }
		//
		// @Override
		// public void takeLeadership(CuratorFramework client) throws Exception {
		// }
		// });
		//
		// // join the members group
		// leaderSelector.start();
		try {
			Thread.sleep(3 * 60000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// wait until the job A is done among all members
		// leaderSelleaderLatchector.close();
	}

	public static void checkExists() {
		int sleepMsBetweenRetries = 100;
		int maxRetries = 3;
		RetryPolicy retryPolicy = new RetryNTimes(maxRetries, sleepMsBetweenRetries);

		CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);

		client.start();
		AsyncCuratorFramework async = AsyncCuratorFramework.wrap(client);

		AtomicBoolean exists = new AtomicBoolean(false);

		async.checkExists().forPath("/").thenAcceptAsync(s -> exists.set(s != null));

		// await().until(() -> assertThat(exists.get()).isTrue());
	}

	public static void main(String[] args) throws Exception {
		leaderSelector();
	}
}
