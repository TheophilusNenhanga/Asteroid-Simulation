package jbq061.assignment4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublishSubscribe {
	Map<String, List<Subscriber>> channels = new HashMap<>();

	/**
	 * Creates a new channel with an empty list of subscribers
	 * @param channelName The name of the channel to be created
	 */
	private void createChannel(String channelName) {
		channels.put(channelName, new ArrayList<>());
	}

	/**
	 * Adds a subscriber to a channel.
	 * If the channel does not exist a new channel is created.
	 * @param channelName Name of the channels to which the subscriber will be added.
	 * @param subscriber The subscriber to be added to a channel
	 */
	public void subscribe(String channelName, Subscriber subscriber) {
		if (channels.containsKey(channelName)) {
			channels.get(channelName).add(subscriber);
			return;
		}
		createChannel(channelName);
		subscribe(channelName, subscriber);
	}

	/**
	 * Send a notification to all subscribers of a channel
	 * @param channelName the channel whose subscribers will be notified
	 * @param changedState The notification state change
	 */
	public void publish(String channelName, Object changedState) {
		if (channels.containsKey(channelName)) {
			channels.get(channelName).forEach(subscriber -> subscriber.receiveNotification(channelName, changedState));
		}
	}
}
