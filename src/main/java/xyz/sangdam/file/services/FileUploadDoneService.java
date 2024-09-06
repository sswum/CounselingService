package xyz.sangdam.file.services;

import lombok.RequiredArgsConstructor;
import xyz.sangdam.file.constants.FileStatus;
import xyz.sangdam.file.entities.FileInfo;
import xyz.sangdam.file.repositories.FileInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileUploadDoneService {
    private final FileInfoRepository repository;
    private final FileInfoService infoService;

    public void process(String gid, String location) {

        List<FileInfo> items = infoService.getList(gid, location, FileStatus.ALL);
        items.forEach(i -> i.setDone(true));

        repository.saveAllAndFlush(items);
    }

    public void process(String gid) {
        process(gid, null);
    }
}
