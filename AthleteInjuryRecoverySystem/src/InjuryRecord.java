public class InjuryRecord {
    private String athleteName;
    private String injuryType;
    private int recoveryTime;

    public InjuryRecord(String athleteName, String injuryType, int recoveryTime) {
        this.athleteName = athleteName;
        this.injuryType = injuryType;
        this.recoveryTime = recoveryTime;
    }

    public String getAthleteName() {
        return athleteName;
    }

    public String getInjuryType() {
        return injuryType;
    }

    public int getRecoveryTime() {
        return recoveryTime;
    }
}
