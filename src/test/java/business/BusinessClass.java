package business;

import org.apache.logging.log4j.Logger;
import utils.LoggerUtil;

public class BusinessClass {
    private static final Logger logger = LoggerUtil.getLogger(BusinessClass.class);

    public void businessMethod() {
        logger.info("This is a business method.");
    }
}