package com.viseguardstudios.asteroid_miner.util;

public interface INotifyPropertyChanged {

    public void addListener(StateChangedListener l);


    public void removeListener(StateChangedListener l);

}

