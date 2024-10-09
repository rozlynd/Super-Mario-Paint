package smp.components.staff.sequences;

import java.io.Serializable;
import java.util.ArrayList;

import smp.components.Values;
import smp.stateMachine.TimeSignature;

/**
 * We might not even need MIDI to do this sequencing stuff. This class keeps
 * track of whatever is displaying on the staff right now.
 *
 * @author RehdBlob
 * @since 2013.08.23
 */
public class StaffSequence implements Serializable {

    /**
     * Generated serial ID.
     */
    private static final long serialVersionUID = 5752285850525402081L;

    /**
     * The tempo of this sequence.
     */
    private double tempo = Values.DEFAULT_TEMPO;

    /** These are all of the lines on the staff. */
    private ArrayList<StaffNoteLine> theLines;

    /** This tells us which notes are extended (green highlight) or not. */
    private boolean[] noteExtensions = new boolean[Values.NUMINSTRUMENTS];

    /** The time signature of this sequence. */
    private TimeSignature t = TimeSignature.FOUR_FOUR;
    
    /** The soundset bound to and should be loaded for this sequence. */
    private String soundsetBinding = "";

    /** Default constructor. Makes an empty song. */
    public StaffSequence() {
        theLines = new ArrayList<StaffNoteLine>();
        for (int i = 0; i < Values.DEFAULT_LINES_PER_SONG; i++)
            theLines.add(new StaffNoteLine());
    }
    
    public int getLength() {
        return theLines.size();
    }
    
    /**
     * <p>Add empty lines to set the length of the sequence.</p>
     * 
     * <p>Without effect if the length is already greater or equal.</p>
     * 
     * @param n the desired length
     */
    public void resize(int n) {
        int currentSize = theLines.size();
        for (int i = 0; i < n - currentSize; i++) {
            theLines.add(new StaffNoteLine());
        }   
    }
    
    /**
     * <p>Compute the index of the line marking the end of this sequence,
     * <i>i.e.</i> the first line that should <b>not</b> be played.</p>
     * 
     * <p>Takes into account the time signature (length of a bar).</p>
     */
    public int getEndlineIndex() {
        int lastNonempty = theLines.size() - 1;
        while (lastNonempty >= 0 && theLines.get(lastNonempty).isEmpty()) {
            lastNonempty--;
        }
        if (lastNonempty < 0) {
            return 0;
        } else {
            int barLength = t.top();
            // return first multiple of barLength that is > lastNonempty
            return barLength * ((lastNonempty / barLength) + 1);
        }
    }

    /**
     * @param i
     *            The index that we want to get some line from.
     * @return Gets a <code>StaffNoteLine</code> that resides at index i.
     */
    public StaffNoteLine getLine(int i) {
        try {
            return theLines.get(i);
        } catch (IndexOutOfBoundsException e) {
            resize(i + 1);
            return getLine(i);
        }
    }

    /**
     * @return The entire list of the StaffNoteLines of this song.
     */
    public ArrayList<StaffNoteLine> getTheLines() {
        return theLines;
    }

    /**
     * @param i
     *            The index that we want to modify.
     * @param s
     *            The StaffNoteLine that we want to place at this index.
     */
    public void setLine(int i, StaffNoteLine s) {
        theLines.set(i, s);
    }

    /**
     * Adds a line into this sequence.
     *
     * @param s
     *            The StaffNoteLine that we want to add.
     */
    public void addLine(StaffNoteLine s) {
        theLines.add(s);
    }

    /**
     * Adds a line into this sequence.
     *
     * @param i
     *            The index at which we want to add this line.
     * @param s
     *            The StaffNoteLine that we want to add.
     */
    public void addLine(int i, StaffNoteLine s) {
        theLines.add(i, s);
    }

    /**
     * Removes a line from this sequence.
     *
     * @param s
     *            The StaffNoteLine that we want to delete.
     */
    public void deleteLine(StaffNoteLine s) {
        theLines.remove(s);
    }

    /**
     * Removes a line from this sequence.
     *
     * @param i
     *            The line index we want to delete from.
     */
    public void deleteLine(int i) {
        theLines.remove(i);
    }

    /** @return The tempo of this sequence. */
    public double getTempo() {
        return tempo;
    }

    /**
     * Sets the tempo of this sequence.
     *
     * @param t
     *            The tempo of this sequence.
     */
    public void setTempo(double t) {
        tempo = t;
    }

    /**
     * @param i
     *            The note extensions bitfield that we want to set.
     */
    public void setNoteExtensions(boolean[] i) {
        noteExtensions = i;
    }

    /** @return The bitfield denoting which notes are extended. */
    public boolean[] getNoteExtensions() {
        return noteExtensions;
    }

    /**
     * @param s
     *            The time signature to set this <code>StaffSequence</code> to.
     */
    public void setTimeSignature(String s) {
        int top = Integer.parseInt(s.substring(0, s.indexOf("/")));
        int bottom = Integer.parseInt(s.substring(s.indexOf("/") + 1));
        for (TimeSignature tSig : TimeSignature.values()) {
            if (tSig.bottom() == bottom && tSig.top() == top) {
                t = tSig;
                break;
            }
        }
        if (t == null) {
            t = TimeSignature.FOUR_FOUR;
        }
    }

    /** @return The time signature of this sequence. */
    public TimeSignature getTimeSignature() {
        return t;
    }

	/**
	 * Sets the soundset for this sequence which should be loaded with the
	 * sequence.
	 * @since v1.1.2
	 */
	public void setSoundset(String soundset) {
		soundsetBinding = soundset;
    }

	/**
	 * @return The soundset bound to this sequence.
	 * @since v1.1.2
	 */
	public String getSoundset() {
		return soundsetBinding;
    }
    
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append("Tempo = " + tempo + "\n");
        out.append("Extensions = " + noteExtensions + "\n");
        out.append(theLines.toString() + "\n");
        return out.toString();
    }

}
