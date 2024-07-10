package org.geysermc.api.util;

/**
 * Represents a version of the api.
 */
public class ApiVersion {
    private final int human;
    private final int major;
    private final int minor;

    public ApiVersion(int human, int minor, int patch) {
        this.human = human;
        this.major = minor;
        this.minor = patch;
    }

    /**
     * Returns the human version of the api.
     *
     * @return the human version
     */
    public int human() {
        return this.human;
    }

    /**
     * Returns the major version of the api.
     *
     * @return the major version
     */
    public int major() {
        return this.major;
    }

    /**
     * Returns the minor version of the api.
     *
     * @return the minor version
     */
    public int minor() {
        return this.minor;
    }

    /**
     * Checks whether the requested version is compatible with this version.
     * The parameters represent the desired api version, which is checked against this version.
     * The human version must match, and the desired major version must be equal or smaller than this major version.
     * If the major versions match, then the desired minor version must be equal or smaller than this minor version.
     *
     * @param human the desired human version
     * @param major the desired major version
     * @param minor the desired minor version
     * @return true if the requested api version is supported by the present API version
     */
    public boolean supportsRequestedVersion(int human, int major, int minor) throws IncompatibleVersionException {
        if (human != this.human) {
            throw new IncompatibleVersionException(IncompatibleVersionException.Reason.HUMAN_DIFFER);
        }

        if (major > this.major) {
            throw new IncompatibleVersionException(IncompatibleVersionException.Reason.MAJOR_DIFFER);
        }

        if (minor > this.minor && major >= this.major) {
            throw new IncompatibleVersionException(IncompatibleVersionException.Reason.MINOR_DIFFER);
        }

        return true;
    }

    /**
     * An exception thrown in {@link #supportsRequestedVersion(int, int, int)} when there is a version mismatch.
     */
    public static class IncompatibleVersionException extends Exception {
        private static final long serialVersionUID = 1L;
        private final IncompatibleVersionException.Reason reason;

        public IncompatibleVersionException(org.geysermc.api.util.ApiVersion.IncompatibleVersionException.Reason reason) {
            super(reason.cause());
            this.reason = reason;
        }

        public IncompatibleVersionException.Reason reason() {
            return this.reason;
        }

        public enum Reason {
            HUMAN_DIFFER("The human api version does not match!"),
            MAJOR_DIFFER("The major api version is too high!"),
            MINOR_DIFFER("The minor api version is too high!");

            private final String cause;

            Reason(String cause) {
                this.cause = cause;
            }

            public String cause() {
                return this.cause;
            }
        }
    }
}
