/*
 * Copyright (C) 2015 HotFlow
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.ItemPlus.Core.v1_0_0.IO;

import com.ItemPlus.ItemPlus;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 配置文本写出
 * <p>
 * @author HotFlow
 */
public final class FileWritter
{
    private final File file;

    /**
     * 构造Writter
     * <p>
     * @param file 文件
     */
    public FileWritter(File file)
    {
        this.file = file;
    }

    public void write(int index, String s)
    {
        if (!this.file.exists())
        {
            return;
        }

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
            reader.close();
            file.delete();

            if (index >= lines.size())
            {
                lines.add(s);
            }

            for (int i = 0; i < lines.size(); i++)
            {
                if (i == index)
                {
                    lines.set(index, s);
                }
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < lines.size(); i++)
            {
                writer.write(new String(lines.get(i).getBytes("gb2312"), "gb2312"));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException ex)
        {
            ItemPlus.logger.getLogger().warning(ex.toString());
        }
    }

    public void write(String s)
    {
        if (!this.file.exists())
        {
            return;
        }

        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            ArrayList<String> lines = new ArrayList<String>();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                lines.add(line);
            }
            lines.add(s);

            reader.close();
            file.delete();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (int i = 0; i < lines.size(); i++)
            {
                writer.write(new String(lines.get(i).getBytes("gb2312"), "gb2312"));
                writer.newLine();
            }
            writer.close();
        }
        catch (IOException ex)
        {
            ItemPlus.logger.getLogger().warning(ex.toString());
        }
    }
}
