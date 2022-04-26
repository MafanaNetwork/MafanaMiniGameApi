package me.TahaCheji.countdown;

import java.util.EventListener;

public interface CountdownEventListener extends EventListener {

	public void CountdownEventOccurred(CountdownFinishEvent evt);
}
