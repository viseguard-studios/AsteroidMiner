package com.viseguardstudios.asteroid_miner.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * Hasznalando karakterkodok: le - 40
 * 	fel: 38
 *  balra: 37
 *  jobbra: 39
 *  enter: 10
 */

/**
 * A billentyuzet kezelese sajnos akcio alapu, igy kell egy a billentyuzetet reprezentalo tabla, amit bizonyos idokozonkent le tudunk kerdezni.
 * @author Adam
 *
 */
public class KeyboardStateTable implements KeyListener{ ///azert kell, mert nem csak egy mozdulatot vegez a banyasz, amikor megnyomjuk a gombot...

	/**
	 * a billentyuket reprezentalo bool tomb. Igaz, ha le van nyomva
	 */
	private boolean[] keys;
	
	/**
	 * Billentyuk allapotanak a lekerdezese
	 * @return
	 */
	public boolean[] getKeyStates() {
		return keys;
	}
	
	/**
	 * Miota van lenyomva az adott billenytu. Nincs hasznalva.
	 */
	private int[] keyPressTime;
	
	
	/**
	 * Foglalkoztunk-e mar a billentyuvel. Hasznos, ha lenyomasra csak egyszer akarunk muveletet vegezni
	 */
	private boolean[] keyStrokeHasBeenAdressed;
	/**
	 * Foglalkoztunk-e mar a billentyuvel.
	 * @return 
	 */
	public boolean[] getKeyStrokeHasBeeenAdressed() {
		return keyStrokeHasBeenAdressed;
	}

	/**
	 * Annak a beallitasa, hogy egy adott billentyuvel foglalkoztunk mar-e
	 * @param keyCode melyik billentyu
	 * @param keyStrokeHasBeeenAdressed foglalkoztunk-e mar vele
	 */
	public void setKeyStrokeHasBeenAdressed(int keyCode, boolean keyStrokeHasBeeenAdressed) {
		this.keyStrokeHasBeenAdressed[keyCode] = keyStrokeHasBeeenAdressed;
	}

	/**
	 * Egyszeru konstruktor.
	 * Feltetelezzuk, hogy extended ascii szerint 256 billentyu van.
	 */
	public KeyboardStateTable() {
		keys = new boolean[256];
		keyPressTime = new int[256];
		keyStrokeHasBeenAdressed= new boolean[256];
		
		for(int i = 0; i<256;i++) {
			keys[i] = false;
			keyPressTime[i] = 0;
			keyStrokeHasBeenAdressed[i]=false;
		}
	}
	
	@Override
	/**
	 * mi legyen, ha lenyomtunk egy gombot, az allapot ilyenkor lenyomott lesz
	 */
	public void keyPressed(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if(keyCode >255) keyCode=0;
		keys[keyCode] = true;
		keyPressTime[keyCode]=1;
		keyStrokeHasBeenAdressed[keyCode]=false;
		
	}

	@Override
	/**
	 * mi legyen, ha elengedunk egy gombot. Az allapot ilyenkor elengedett lesz
	 */
	public void keyReleased(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if(keyCode >255) keyCode=0;
		keys[keyCode] = false;
		keyPressTime[keyCode]=0;
		keyStrokeHasBeenAdressed[keyCode]=false;
		
	}

	@Override
	/**
	 * Az operacios rendzerek szeretik a hosszu lenyomast ismetleskent kezelni, igy nekunk is foglalkozni kell vele
	 */
	public void keyTyped(KeyEvent arg0) {
		int keyCode = arg0.getKeyCode();
		if(keyCode >255) keyCode=0;
		keys[keyCode] = true;
		keyPressTime[keyCode]+=1;
		
	}

}
