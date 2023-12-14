package committee.nova.consistentaim.proxy;

public interface IAimingProxy {
    IAimingProxy DUMMY = () -> false;

    boolean isAiming();
}
