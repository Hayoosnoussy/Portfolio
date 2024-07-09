package datacache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import data_cache.data.CellComputerDetail;

import static com.schiedtandbachmann.ParkApplication.LOGGER_NAME;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class CellComputersCache {
    private static final Logger logger = LoggerFactory.getLogger(LOGGER_NAME + ":CellComputersCache");
    private Map<String, CellComputerDetail> syncHashMap = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private static CellComputersCache instance = null;

    public static CellComputersCache getCellComputersCache() {
        logger.debug("Method called: getCellComputersCache returns CellComputersCache");
        if (instance == null)
            instance = new CellComputersCache();
        return instance;
    }

    public void putAllNew(Map<String,CellComputerDetail> syncHashMap) {
        logger.debug("Method called: putAllNew with params - Map<String,CellComputer>:{} returns void", syncHashMap);

        try {
            writeLock.lock();
            this.syncHashMap.clear();
            this.syncHashMap = (syncHashMap != null ? syncHashMap : new HashMap<>());
        } finally {
            writeLock.unlock();
        }
    }

    public boolean containsKey(String key) {
        logger.debug("Method called: containsKey with params - String:{} returns boolean", key);

        try {
            readLock.lock();
            return syncHashMap.containsKey(key);
        } finally {
            readLock.unlock();
        }
    }

    public CellComputerDetail get(String key){
        logger.debug("Method called: get with params - String:{} returns CellComputer", key);

        try {
            readLock.lock();
            return syncHashMap.get(key);
        } finally {
            readLock.unlock();
        }
    }

    public Map<String, CellComputerDetail> getAll(){
        logger.debug("Method called: getAll returns Map<String, CellComputer>");

        try {
            readLock.lock();
            return syncHashMap;
        } finally {
            readLock.unlock();
        }
    }

    public List<CellComputerDetail> getAllList() {
        logger.debug("Method called: getAllList returns List<CellComputer>");

        List<CellComputerDetail> list;
        try {
            readLock.lock();
            list = new ArrayList<>(syncHashMap.values());

        } finally {
            readLock.unlock();
        }

        return list;
    }

    public void put(String key, CellComputerDetail value) {
        logger.debug("Method called: put with params - String:{}, CellComputer:{} returns void", key, value);

        try {
            writeLock.lock();
            syncHashMap.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public CellComputerDetail remove(String key){
        logger.debug("Method called: remove with params - String:{} returns CellComputer", key);

        try {
            writeLock.lock();
            return syncHashMap.remove(key);
        } finally {
            writeLock.unlock();
        }
    }

    public static String getCellKey(CellComputerDetail cell) {
        logger.debug("Method called: getCellKey with params - CellComputer:{} returns String", cell);

        if (cell != null) {
            return getCellKey(cell.getOperatorData() != null ? cell.getOperatorData().getOperatorId() : -1,
                    cell.getCellComputer());
        }
        return "null";
    }

    public static String getCellKey(Integer operator, Integer cellComputer) {
        return operator + "@" + cellComputer;
    }

    public void updateShiftData(String key, long shiftId, long shiftNo) {
        logger.debug("Method called: updateShiftData with params - String:{}, long:{}, Integer:{} long CellComputer", key, shiftId, shiftNo);

        try {
            writeLock.lock();
            CellComputerDetail cellComputerDetail = syncHashMap.get(key);
            if (cellComputerDetail != null) {
                cellComputerDetail.setShiftId(shiftId);
                cellComputerDetail.setShiftNo(shiftNo);
            }
        } finally {
            writeLock.unlock();
        }
    }
}
