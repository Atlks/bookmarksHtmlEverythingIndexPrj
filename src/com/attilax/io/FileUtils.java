package com.attilax.coreLuni.io;
    import java.io.BufferedInputStream;  
    import java.io.ByteArrayOutputStream;  
    import java.io.File;  
    import java.io.FileInputStream;  
    import java.io.FileNotFoundException;  
    import java.io.IOException;  
    import java.io.RandomAccessFile;  
    import java.nio.ByteBuffer;  
    import java.nio.MappedByteBuffer;  
    import java.nio.channels.FileChannel;  
import java.nio.channels.FileChannel.MapMode;  
      
      
    public class FileUtils {  
      
        /** 
         * the traditional io way  
         * @param filename 
         * @return 
         * @throws IOException 
         */  
        public static byte[] toByteArray(String filename) throws IOException{  
              
            File f = new File(filename);  
            if(!f.exists()){  
                throw new FileNotFoundException(filename);  
            }  
      
            ByteArrayOutputStream bos = new ByteArrayOutputStream((int)f.length());  
            BufferedInputStream in = null;  
            try{  
                in = new BufferedInputStream(new FileInputStream(f));  
                int buf_size = 1024;  
                byte[] buffer = new byte[buf_size];  
                int len = 0;  
                while(-1 != (len = in.read(buffer,0,buf_size))){  
                    bos.write(buffer,0,len);  
                }  
                return bos.toByteArray();  
            }catch (IOException e) {  
                e.printStackTrace();  
                throw e;  
            }finally{  
                try{  
                    in.close();  
                }catch (IOException e) {  
                    e.printStackTrace();  
                }  
                bos.close();  
            }  
        }  
          
          
        /** 
         * NIO way 
         * @param filename 
         * @return 
         * @throws IOException 
         */  
        public static byte[] toByteArray2(String filename)throws IOException{  
              
            File f = new File(filename);  
            if(!f.exists()){  
                throw new FileNotFoundException(filename);  
            }  
              
            FileChannel channel = null;  
            FileInputStream fs = null;  
            try{  
                fs = new FileInputStream(f);  
                channel = fs.getChannel();  
                ByteBuffer byteBuffer = ByteBuffer.allocate((int)channel.size());  
                while((channel.read(byteBuffer)) > 0){  
                    // do nothing  
    //              System.out.println("reading");  
                }  
                return byteBuffer.array();  
            }catch (IOException e) {  
                e.printStackTrace();  
                throw e;  
            }finally{  
                try{  
                    channel.close();  
                }catch (IOException e) {  
                    e.printStackTrace();  
                }  
                try{  
                    fs.close();  
                }catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
          
          
        /**  todox  perform
         * Mapped File  way 
         * MappedByteBuffer 可以在处理大文件时，提升性能 
         * @param filename 
         * @return 
         * @throws IOException 
         */  
        public static byte[] toByteArray3(String filename)throws IOException{  
              
            FileChannel fc = null;  
            try{  
                fc = new RandomAccessFile(filename,"r").getChannel();  
                MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0, fc.size()).load();  
                System.out.println(byteBuffer.isLoaded());  
                byte[] result = new byte[(int)fc.size()];  
                if (byteBuffer.remaining() > 0) {  
    //              System.out.println("remain");  
                    byteBuffer.get(result, 0, byteBuffer.remaining());  
                }  
                return result;  
            }catch (IOException e) {  
                e.printStackTrace();  
                throw e;  
            }finally{  
                try{  
                    fc.close();  
                }catch (IOException e) {  
                    e.printStackTrace();  
                }  
            }  
        }


        /**

         * 生产文件 path 如果文件所在路径不存在则生成路径

         * 

         * @param fileName

         *            文件名 带路径

         * @param isDirectory 是否为路径

         * @return

         * @author yayagepei

         * @date 2008-8-27

         */

        public static File buildFile(String fileName, boolean isDirectory) {

            File target = new File(fileName);

            if (isDirectory) {

                target.mkdirs();

            } else {

                if (!target.getParentFile().exists()) {

                    target.getParentFile().mkdirs();

                    target = new File(target.getAbsolutePath());

                }

            }

            return target;

        }
    }  