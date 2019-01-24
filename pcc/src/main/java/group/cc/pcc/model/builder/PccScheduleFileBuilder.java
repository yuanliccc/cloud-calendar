package group.cc.pcc.model.builder;

import group.cc.pcc.model.PccScheduleFile;

import java.util.ArrayList;
import java.util.List;

public class PccScheduleFileBuilder {

    public static PccScheduleFile build(Integer pccScheduleId, Integer pccUserId, Integer pccFileId) {

        PccScheduleFile pccScheduleFile = new PccScheduleFile();

        pccScheduleFile.setPccFileId(pccFileId);
        pccScheduleFile.setPccScheduleId(pccScheduleId);
        pccScheduleFile.setPccUserId(pccUserId);

        return pccScheduleFile;
    }

    public static List<PccScheduleFile> build(Integer[] pccFileIds, Integer pccScheduleId, Integer pccUserId) {

        List<PccScheduleFile> pccScheduleFiles = new ArrayList<>(pccFileIds.length);

        for(int i = 0; i < pccFileIds.length; i++) {
            pccScheduleFiles.add(build(pccScheduleId, pccUserId, pccFileIds[i]));
        }

        return pccScheduleFiles;
    }
}
