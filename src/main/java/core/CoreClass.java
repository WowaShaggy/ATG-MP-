package core;

import org.apache.logging.log4j.Logger;
import utils.LoggerUtil;

public class CoreClass {
    private static final Logger logger = LoggerUtil.getLogger(CoreClass.class);

    public void coreMethod() {
        logger.info("This is a core method.");
    }
}