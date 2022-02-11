package com.minacloud.file.dto.cmd;

import com.alibaba.cola.dto.Command;
import com.minacloud.file.dto.clientobject.FileChunkInfoCO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class SaveFileChunkInfoCmd extends Command {
    private FileChunkInfoCO fileChunkInfo;

}
