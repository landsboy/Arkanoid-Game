package interfaces;
/**
 * this interface represents a object that can notify other objects obout something.
 */
public interface HitNotifier {
    /**
     * this method adds a hit listener object as a listener to hit events.
     * @param hl the hit listener object.
     */
    void addHitListener(HitListener hl);
    /**
     * this method removes a given hit listener object from the list of listeners to hit events.
     * @param hl the hit listener object.
     */
    void removeHitListener(HitListener hl);
}