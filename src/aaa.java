//// Decompiled by DJ v2.9.9.60 Copyright 2000 Atanas Neshkov  Date: 2019/5/23 9:45:21
//// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
//// Decompiler options: packimports(3) 
//
//package net.coobird.thumbnailator;
//
//import java.awt.Dimension;
//import java.awt.Rectangle;
//import java.awt.RenderingHints;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.IOException;
//import java.io.InputStream;
//import java.io.OutputStream;
//import java.net.URL;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Set;
//import net.coobird.thumbnailator.filters.ImageFilter;
//import net.coobird.thumbnailator.filters.Pipeline;
//import net.coobird.thumbnailator.filters.Rotation;
//import net.coobird.thumbnailator.filters.Watermark;
//import net.coobird.thumbnailator.geometry.AbsoluteSize;
//import net.coobird.thumbnailator.geometry.Coordinate;
//import net.coobird.thumbnailator.geometry.Position;
//import net.coobird.thumbnailator.geometry.Positions;
//import net.coobird.thumbnailator.geometry.Region;
//import net.coobird.thumbnailator.geometry.Size;
//import net.coobird.thumbnailator.name.Rename;
//import net.coobird.thumbnailator.resizers.BicubicResizer;
//import net.coobird.thumbnailator.resizers.BilinearResizer;
//import net.coobird.thumbnailator.resizers.ProgressiveBilinearResizer;
//import net.coobird.thumbnailator.resizers.Resizer;
//import net.coobird.thumbnailator.resizers.Resizers;
//import net.coobird.thumbnailator.resizers.configurations.AlphaInterpolation;
//import net.coobird.thumbnailator.resizers.configurations.Antialiasing;
//import net.coobird.thumbnailator.resizers.configurations.Dithering;
//import net.coobird.thumbnailator.resizers.configurations.Rendering;
//import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
//import net.coobird.thumbnailator.tasks.SourceSinkThumbnailTask;
//import net.coobird.thumbnailator.tasks.io.BufferedImageSink;
//import net.coobird.thumbnailator.tasks.io.BufferedImageSource;
//import net.coobird.thumbnailator.tasks.io.FileImageSink;
//import net.coobird.thumbnailator.tasks.io.FileImageSource;
//import net.coobird.thumbnailator.tasks.io.ImageSource;
//import net.coobird.thumbnailator.tasks.io.InputStreamImageSource;
//import net.coobird.thumbnailator.tasks.io.OutputStreamImageSink;
//import net.coobird.thumbnailator.tasks.io.URLImageSource;
//import net.coobird.thumbnailator.util.ThumbnailatorUtils;
//
//// Referenced classes of package net.coobird.thumbnailator:
////            ThumbnailParameter, Thumbnails, Thumbnailator
//
//public static class Status.OPTIONAL
//{
//    private static final class Properties extends Enum
//        implements Property
//    {
//
//        public static final Properties[] values()
//        {
//            return (Properties[])$VALUES.clone();
//        }
//
//        public static Properties valueOf(String s)
//        {
//            return (Properties)Enum.valueOf(net/coobird/thumbnailator/Thumbnails$Builder$Properties, s);
//        }
//
//        public String getName()
//        {
//            return name;
//        }
//
//        public static final Properties SIZE;
//        public static final Properties WIDTH;
//        public static final Properties HEIGHT;
//        public static final Properties SCALE;
//        public static final Properties IMAGE_TYPE;
//        public static final Properties SCALING_MODE;
//        public static final Properties ALPHA_INTERPOLATION;
//        public static final Properties ANTIALIASING;
//        public static final Properties DITHERING;
//        public static final Properties RENDERING;
//        public static final Properties KEEP_ASPECT_RATIO;
//        public static final Properties OUTPUT_FORMAT;
//        public static final Properties OUTPUT_FORMAT_TYPE;
//        public static final Properties OUTPUT_QUALITY;
//        public static final Properties RESIZER;
//        public static final Properties SOURCE_REGION;
//        public static final Properties ALLOW_OVERWRITE;
//        private final String name;
//        private static final Properties $VALUES[];
//
//        static 
//        {
//            SIZE = new Properties("SIZE", 0, "size");
//            WIDTH = new Properties("WIDTH", 1, "width");
//            HEIGHT = new Properties("HEIGHT", 2, "height");
//            SCALE = new Properties("SCALE", 3, "scale");
//            IMAGE_TYPE = new Properties("IMAGE_TYPE", 4, "imageType");
//            SCALING_MODE = new Properties("SCALING_MODE", 5, "scalingMode");
//            ALPHA_INTERPOLATION = new Properties("ALPHA_INTERPOLATION", 6, "alphaInterpolation");
//            ANTIALIASING = new Properties("ANTIALIASING", 7, "antialiasing");
//            DITHERING = new Properties("DITHERING", 8, "dithering");
//            RENDERING = new Properties("RENDERING", 9, "rendering");
//            KEEP_ASPECT_RATIO = new Properties("KEEP_ASPECT_RATIO", 10, "keepAspectRatio");
//            OUTPUT_FORMAT = new Properties("OUTPUT_FORMAT", 11, "outputFormat");
//            OUTPUT_FORMAT_TYPE = new Properties("OUTPUT_FORMAT_TYPE", 12, "outputFormatType");
//            OUTPUT_QUALITY = new Properties("OUTPUT_QUALITY", 13, "outputQuality");
//            RESIZER = new Properties("RESIZER", 14, "resizer");
//            SOURCE_REGION = new Properties("SOURCE_REGION", 15, "sourceRegion");
//            ALLOW_OVERWRITE = new Properties("ALLOW_OVERWRITE", 16, "allowOverwrite");
//            $VALUES = (new Properties[] {
//                SIZE, WIDTH, HEIGHT, SCALE, IMAGE_TYPE, SCALING_MODE, ALPHA_INTERPOLATION, ANTIALIASING, DITHERING, RENDERING, 
//                KEEP_ASPECT_RATIO, OUTPUT_FORMAT, OUTPUT_FORMAT_TYPE, OUTPUT_QUALITY, RESIZER, SOURCE_REGION, ALLOW_OVERWRITE
//            });
//        }
//
//        private Properties(String s, int i, String s1)
//        {
//            super(s, i);
//            name = s1;
//        }
//    }
//
//    private static interface Property
//    {
//
//        public abstract String getName();
//    }
//
//    private static final class Status extends Enum
//    {
//
//        public static final Status[] values()
//        {
//            return (Status[])$VALUES.clone();
//        }
//
//        public static Status valueOf(String s)
//        {
//            return (Status)Enum.valueOf(net/coobird/thumbnailator/Thumbnails$Builder$Status, s);
//        }
//
//        public static final Status OPTIONAL;
//        public static final Status READY;
//        public static final Status NOT_READY;
//        public static final Status ALREADY_SET;
//        public static final Status CANNOT_SET;
//        private static final Status $VALUES[];
//
//        static 
//        {
//            OPTIONAL = new Status("OPTIONAL", 0);
//            READY = new Status("READY", 1);
//            NOT_READY = new Status("NOT_READY", 2);
//            ALREADY_SET = new Status("ALREADY_SET", 3);
//            CANNOT_SET = new Status("CANNOT_SET", 4);
//            $VALUES = (new Status[] {
//                OPTIONAL, READY, NOT_READY, ALREADY_SET, CANNOT_SET
//            });
//        }
//
//        private Status(String s, int i)
//        {
//            super(s, i);
//        }
//    }
//
//    private final class BufferedImageIterable
//        implements Iterable
//    {
//
//        public Iterator iterator()
//        {
//            return new Iterator() {
//
//                public boolean hasNext()
//                {
//                    return sourceIter.hasNext();
//                }
//
//                public BufferedImage next()
//                {
//                    ImageSource imagesource = (ImageSource)sourceIter.next();
//                    BufferedImageSink bufferedimagesink = new BufferedImageSink();
//                    try
//                    {
//                        Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(makeParam(), imagesource, bufferedimagesink));
//                    }
//                    catch(IOException ioexception)
//                    {
//                        return null;
//                    }
//                    return bufferedimagesink.getSink();
//                }
//
//                public void remove()
//                {
//                    throw new UnsupportedOperationException("Cannot remove elements from this iterator.");
//                }
//
//                public volatile Object next()
//                {
//                    return next();
//                }
//
//                Iterator sourceIter;
//                final BufferedImageIterable this$1;
//
//                
//                {
//                    this$1 = BufferedImageIterable.this;
//                    super();
//                    sourceIter = sources.iterator();
//                }
//            };
//        }
//
//        final Thumbnails.Builder this$0;
//
//        private BufferedImageIterable()
//        {
//            this$0 = Thumbnails.Builder.this;
//            super();
//        }
//
//        BufferedImageIterable(Thumbnails._cls1 _pcls1)
//        {
//            this();
//        }
//    }
//
//    private static final class BufferedImageImageSourceIterator
//        implements Iterable
//    {
//
//        public Iterator iterator()
//        {
//            return new Iterator() {
//
//                public boolean hasNext()
//                {
//                    return iter.hasNext();
//                }
//
//                public ImageSource next()
//                {
//                    return new BufferedImageSource((BufferedImage)iter.next());
//                }
//
//                public void remove()
//                {
//                    throw new UnsupportedOperationException();
//                }
//
//                public volatile Object next()
//                {
//                    return next();
//                }
//
//                Iterator iter;
//                final BufferedImageImageSourceIterator this$0;
//
//                
//                {
//                    this$0 = BufferedImageImageSourceIterator.this;
//                    super();
//                    iter = image.iterator();
//                }
//            };
//        }
//
//        private final Iterable image;
//
//
//        private BufferedImageImageSourceIterator(Iterable iterable)
//        {
//            image = iterable;
//        }
//
//        BufferedImageImageSourceIterator(Iterable iterable, Thumbnails._cls1 _pcls1)
//        {
//            this(iterable);
//        }
//    }
//
//    private static final class InputStreamImageSourceIterator
//        implements Iterable
//    {
//
//        public Iterator iterator()
//        {
//            return new Iterator() {
//
//                public boolean hasNext()
//                {
//                    return iter.hasNext();
//                }
//
//                public ImageSource next()
//                {
//                    return new InputStreamImageSource((InputStream)iter.next());
//                }
//
//                public void remove()
//                {
//                    throw new UnsupportedOperationException();
//                }
//
//                public volatile Object next()
//                {
//                    return next();
//                }
//
//                Iterator iter;
//                final InputStreamImageSourceIterator this$0;
//
//                
//                {
//                    this$0 = InputStreamImageSourceIterator.this;
//                    super();
//                    iter = inputStreams.iterator();
//                }
//            };
//        }
//
//        private final Iterable inputStreams;
//
//
//        private InputStreamImageSourceIterator(Iterable iterable)
//        {
//            inputStreams = iterable;
//        }
//
//        InputStreamImageSourceIterator(Iterable iterable, Thumbnails._cls1 _pcls1)
//        {
//            this(iterable);
//        }
//    }
//
//    private static final class URLImageSourceIterator
//        implements Iterable
//    {
//
//        public Iterator iterator()
//        {
//            return new Iterator() {
//
//                public boolean hasNext()
//                {
//                    return iter.hasNext();
//                }
//
//                public ImageSource next()
//                {
//                    return new URLImageSource((URL)iter.next());
//                }
//
//                public void remove()
//                {
//                    throw new UnsupportedOperationException();
//                }
//
//                public volatile Object next()
//                {
//                    return next();
//                }
//
//                Iterator iter;
//                final URLImageSourceIterator this$0;
//
//                
//                {
//                    this$0 = URLImageSourceIterator.this;
//                    super();
//                    iter = urls.iterator();
//                }
//            };
//        }
//
//        private final Iterable urls;
//
//
//        private URLImageSourceIterator(Iterable iterable)
//        {
//            urls = iterable;
//        }
//
//        URLImageSourceIterator(Iterable iterable, Thumbnails._cls1 _pcls1)
//        {
//            this(iterable);
//        }
//    }
//
//    private static final class FileImageSourceIterator
//        implements Iterable
//    {
//
//        public Iterator iterator()
//        {
//            return new Iterator() {
//
//                public boolean hasNext()
//                {
//                    return iter.hasNext();
//                }
//
//                public ImageSource next()
//                {
//                    return new FileImageSource((File)iter.next());
//                }
//
//                public void remove()
//                {
//                    throw new UnsupportedOperationException();
//                }
//
//                public volatile Object next()
//                {
//                    return next();
//                }
//
//                Iterator iter;
//                final FileImageSourceIterator this$0;
//
//                
//                {
//                    this$0 = FileImageSourceIterator.this;
//                    super();
//                    iter = files.iterator();
//                }
//            };
//        }
//
//        private final Iterable files;
//
//
//        private FileImageSourceIterator(Iterable iterable)
//        {
//            files = iterable;
//        }
//
//        FileImageSourceIterator(Iterable iterable, Thumbnails._cls1 _pcls1)
//        {
//            this(iterable);
//        }
//    }
//
//    private static final class StringImageSourceIterator
//        implements Iterable
//    {
//
//        public Iterator iterator()
//        {
//            return new Iterator() {
//
//                public boolean hasNext()
//                {
//                    return iter.hasNext();
//                }
//
//                public ImageSource next()
//                {
//                    return new FileImageSource((String)iter.next());
//                }
//
//                public void remove()
//                {
//                    throw new UnsupportedOperationException();
//                }
//
//                public volatile Object next()
//                {
//                    return next();
//                }
//
//                Iterator iter;
//                final StringImageSourceIterator this$0;
//
//                
//                {
//                    this$0 = StringImageSourceIterator.this;
//                    super();
//                    iter = filenames.iterator();
//                }
//            };
//        }
//
//        private final Iterable filenames;
//
//
//        private StringImageSourceIterator(Iterable iterable)
//        {
//            filenames = iterable;
//        }
//
//        StringImageSourceIterator(Iterable iterable, Thumbnails._cls1 _pcls1)
//        {
//            this(iterable);
//        }
//    }
//
//
//    private static StringImageSourceIterator ofStrings(Iterable iterable)
//    {
//        StringImageSourceIterator stringimagesourceiterator = new StringImageSourceIterator(iterable, null);
//        return new <init>(stringimagesourceiterator);
//    }
//
//    private static <init> ofFiles(Iterable iterable)
//    {
//        FileImageSourceIterator fileimagesourceiterator = new FileImageSourceIterator(iterable, null);
//        return new <init>(fileimagesourceiterator);
//    }
//
//    private static <init> ofUrls(Iterable iterable)
//    {
//        URLImageSourceIterator urlimagesourceiterator = new URLImageSourceIterator(iterable, null);
//        return new <init>(urlimagesourceiterator);
//    }
//
//    private static <init> ofInputStreams(Iterable iterable)
//    {
//        InputStreamImageSourceIterator inputstreamimagesourceiterator = new InputStreamImageSourceIterator(iterable, null);
//        return new <init>(inputstreamimagesourceiterator);
//    }
//
//    private static <init> ofBufferedImages(Iterable iterable)
//    {
//        BufferedImageImageSourceIterator bufferedimageimagesourceiterator = new BufferedImageImageSourceIterator(iterable, null);
//        return new <init>(bufferedimageimagesourceiterator);
//    }
//
//    private void updateStatus(Properties properties, Status status)
//    {
//        if(statusMap.get(properties) == Status.ALREADY_SET)
//            throw new IllegalStateException((new StringBuilder()).append(properties.getName()).append(" is already set.").toString());
//        if(statusMap.get(properties) == Status.CANNOT_SET)
//        {
//            throw new IllegalStateException((new StringBuilder()).append(properties.getName()).append(" cannot be set.").toString());
//        } else
//        {
//            statusMap.put(properties, status);
//            return;
//        }
//    }
//
//    public statusMap size(int i, int j)
//    {
//        updateStatus(Properties.SIZE, Status.ALREADY_SET);
//        updateStatus(Properties.SCALE, Status.CANNOT_SET);
//        Thumbnails.access$1700(i, j);
//        width = i;
//        height = j;
//        return this;
//    }
//
//    public height width(int i)
//    {
//        if(statusMap.get(Properties.SIZE) != Status.CANNOT_SET)
//            updateStatus(Properties.SIZE, Status.CANNOT_SET);
//        if(statusMap.get(Properties.SCALE) != Status.CANNOT_SET)
//            updateStatus(Properties.SCALE, Status.CANNOT_SET);
//        updateStatus(Properties.WIDTH, Status.ALREADY_SET);
//        Thumbnails.access$1700(i, 0x7fffffff);
//        width = i;
//        return this;
//    }
//
//    public width height(int i)
//    {
//        if(statusMap.get(Properties.SIZE) != Status.CANNOT_SET)
//            updateStatus(Properties.SIZE, Status.CANNOT_SET);
//        if(statusMap.get(Properties.SCALE) != Status.CANNOT_SET)
//            updateStatus(Properties.SCALE, Status.CANNOT_SET);
//        updateStatus(Properties.HEIGHT, Status.ALREADY_SET);
//        Thumbnails.access$1700(0x7fffffff, i);
//        height = i;
//        return this;
//    }
//
//    public height forceSize(int i, int j)
//    {
//        updateStatus(Properties.SIZE, Status.ALREADY_SET);
//        updateStatus(Properties.KEEP_ASPECT_RATIO, Status.ALREADY_SET);
//        updateStatus(Properties.SCALE, Status.CANNOT_SET);
//        Thumbnails.access$1700(i, j);
//        width = i;
//        height = j;
//        keepAspectRatio = false;
//        return this;
//    }
//
//    public keepAspectRatio scale(double d)
//    {
//        return scale(d, d);
//    }
//
//    public scale scale(double d, double d1)
//    {
//        updateStatus(Properties.SCALE, Status.ALREADY_SET);
//        updateStatus(Properties.SIZE, Status.CANNOT_SET);
//        updateStatus(Properties.KEEP_ASPECT_RATIO, Status.CANNOT_SET);
//        if(d <= 0.0D || d1 <= 0.0D)
//            throw new IllegalArgumentException("The scaling factor is equal to or less than 0.");
//        if(Double.isNaN(d) || Double.isNaN(d1))
//            throw new IllegalArgumentException("The scaling factor is not a number.");
//        if(Double.isInfinite(d) || Double.isInfinite(d1))
//        {
//            throw new IllegalArgumentException("The scaling factor cannot be infinity.");
//        } else
//        {
//            scaleWidth = d;
//            scaleHeight = d1;
//            return this;
//        }
//    }
//
//    public scaleHeight sourceRegion(Region region)
//    {
//        if(region == null)
//        {
//            throw new NullPointerException("Region cannot be null.");
//        } else
//        {
//            updateStatus(Properties.SOURCE_REGION, Status.ALREADY_SET);
//            sourceRegion = region;
//            return this;
//        }
//    }
//
//    public sourceRegion sourceRegion(Position position, Size size1)
//    {
//        if(position == null)
//            throw new NullPointerException("Position cannot be null.");
//        if(size1 == null)
//            throw new NullPointerException("Size cannot be null.");
//        else
//            return sourceRegion(new Region(position, size1));
//    }
//
//    public it> sourceRegion(int i, int j, int k, int l)
//    {
//        if(k <= 0 || l <= 0)
//            throw new IllegalArgumentException("Width and height must be greater than 0.");
//        else
//            return sourceRegion(((Position) (new Coordinate(i, j))), ((Size) (new AbsoluteSize(k, l))));
//    }
//
//    public ze sourceRegion(Position position, int i, int j)
//    {
//        if(position == null)
//            throw new NullPointerException("Position cannot be null.");
//        if(i <= 0 || j <= 0)
//            throw new IllegalArgumentException("Width and height must be greater than 0.");
//        else
//            return sourceRegion(position, ((Size) (new AbsoluteSize(i, j))));
//    }
//
//    public ze sourceRegion(Rectangle rectangle)
//    {
//        if(rectangle == null)
//            throw new NullPointerException("Region cannot be null.");
//        else
//            return sourceRegion(((Position) (new Coordinate(rectangle.x, rectangle.y))), ((Size) (new AbsoluteSize(rectangle.getSize()))));
//    }
//
//    public ze allowOverwrite(boolean flag)
//    {
//        updateStatus(Properties.ALLOW_OVERWRITE, Status.ALREADY_SET);
//        allowOverwrite = flag;
//        return this;
//    }
//
//    public allowOverwrite imageType(int i)
//    {
//        updateStatus(Properties.IMAGE_TYPE, Status.ALREADY_SET);
//        imageType = i;
//        return this;
//    }
//
//    public ions.ScalingMode scalingMode(ScalingMode scalingmode)
//    {
//        Thumbnails.access$1800(scalingmode, "Scaling mode is null.");
//        updateStatus(Properties.SCALING_MODE, Status.ALREADY_SET);
//        updateStatus(Properties.RESIZER, Status.CANNOT_SET);
//        scalingMode = scalingmode;
//        return this;
//    }
//
//    public scalingMode resizer(Resizer resizer1)
//    {
//        Thumbnails.access$1800(resizer1, "Resizer is null.");
//        updateStatus(Properties.RESIZER, Status.ALREADY_SET);
//        updateStatus(Properties.SCALING_MODE, Status.CANNOT_SET);
//        resizer = resizer1;
//        return this;
//    }
//
//    public ions.AlphaInterpolation alphaInterpolation(AlphaInterpolation alphainterpolation)
//    {
//        Thumbnails.access$1800(alphainterpolation, "Alpha interpolation is null.");
//        updateStatus(Properties.ALPHA_INTERPOLATION, Status.ALREADY_SET);
//        alphaInterpolation = alphainterpolation;
//        return this;
//    }
//
//    public ions.Dithering dithering(Dithering dithering1)
//    {
//        Thumbnails.access$1800(dithering1, "Dithering is null.");
//        updateStatus(Properties.DITHERING, Status.ALREADY_SET);
//        dithering = dithering1;
//        return this;
//    }
//
//    public ions.Antialiasing antialiasing(Antialiasing antialiasing1)
//    {
//        Thumbnails.access$1800(antialiasing1, "Antialiasing is null.");
//        updateStatus(Properties.ANTIALIASING, Status.ALREADY_SET);
//        antialiasing = antialiasing1;
//        return this;
//    }
//
//    public ions.Rendering rendering(Rendering rendering1)
//    {
//        Thumbnails.access$1800(rendering1, "Rendering is null.");
//        updateStatus(Properties.RENDERING, Status.ALREADY_SET);
//        rendering = rendering1;
//        return this;
//    }
//
//    public rendering keepAspectRatio(boolean flag)
//    {
//        if(statusMap.get(Properties.SCALE) == Status.ALREADY_SET)
//            throw new IllegalStateException("Cannot specify whether to keep the aspect ratio if the scaling factor has already been specified.");
//        if(statusMap.get(Properties.SIZE) == Status.NOT_READY)
//            throw new IllegalStateException("Cannot specify whether to keep the aspect ratio unless the size parameter has already been specified.");
//        if((statusMap.get(Properties.WIDTH) == Status.ALREADY_SET || statusMap.get(Properties.HEIGHT) == Status.ALREADY_SET) && !flag)
//        {
//            throw new IllegalStateException("The aspect ratio must be preserved when the width and/or height parameter has already been specified.");
//        } else
//        {
//            updateStatus(Properties.KEEP_ASPECT_RATIO, Status.ALREADY_SET);
//            keepAspectRatio = flag;
//            return this;
//        }
//    }
//
//    public keepAspectRatio outputQuality(float f)
//    {
//        if(f < 0.0F || f > 1.0F)
//        {
//            throw new IllegalArgumentException("The quality setting must be in the range 0.0f and 1.0f, inclusive.");
//        } else
//        {
//            updateStatus(Properties.OUTPUT_QUALITY, Status.ALREADY_SET);
//            outputQuality = f;
//            return this;
//        }
//    }
//
//    public outputQuality outputQuality(double d)
//    {
//        if(d < 0.0D || d > 1.0D)
//            throw new IllegalArgumentException("The quality setting must be in the range 0.0d and 1.0d, inclusive.");
//        updateStatus(Properties.OUTPUT_QUALITY, Status.ALREADY_SET);
//        outputQuality = (float)d;
//        if(outputQuality < 0.0F)
//            outputQuality = 0.0F;
//        else
//        if(outputQuality > 1.0F)
//            outputQuality = 1.0F;
//        return this;
//    }
//
//    public outputQuality outputFormat(String s)
//    {
//        if(!ThumbnailatorUtils.isSupportedOutputFormat(s))
//        {
//            throw new IllegalArgumentException((new StringBuilder()).append("Specified format is not supported: ").append(s).toString());
//        } else
//        {
//            updateStatus(Properties.OUTPUT_FORMAT, Status.ALREADY_SET);
//            outputFormat = s;
//            return this;
//        }
//    }
//
//    public outputFormat outputFormatType(String s)
//    {
//        if(s != ThumbnailParameter.DEFAULT_FORMAT_TYPE && outputFormat == ThumbnailParameter.ORIGINAL_FORMAT)
//            throw new IllegalArgumentException("Cannot set the format type if a specific output format has not been specified.");
//        if(!ThumbnailatorUtils.isSupportedOutputFormatType(outputFormat, s))
//            throw new IllegalArgumentException((new StringBuilder()).append("Specified format type (").append(s).append(") is not ").append(" supported for the format: ").append(outputFormat).toString());
//        updateStatus(Properties.OUTPUT_FORMAT_TYPE, Status.ALREADY_SET);
//        if(!statusMap.containsKey(Properties.OUTPUT_FORMAT))
//            updateStatus(Properties.OUTPUT_FORMAT, Status.CANNOT_SET);
//        outputFormatType = s;
//        return this;
//    }
//
//    public outputFormatType watermark(Watermark watermark1)
//    {
//        if(watermark1 == null)
//        {
//            throw new NullPointerException("Watermark is null.");
//        } else
//        {
//            filterPipeline.add(watermark1);
//            return this;
//        }
//    }
//
//    public  watermark(BufferedImage bufferedimage)
//    {
//        return watermark(((Position) (Positions.CENTER)), bufferedimage, 0.5F);
//    }
//    opacity
//    public CENTER watermark(BufferedImage bufferedimage, float f)
//    {
//        return watermark(((Position) (Positions.CENTER)), bufferedimage, f);
//    }
//
//    public CENTER watermark(Position position, BufferedImage bufferedimage, float f)
//    {
//        filterPipeline.add(new Watermark(position, bufferedimage, f));
//        return this;
//    }
//
//    public init> rotate(double d)
//    {
//        filterPipeline.add(Rotation.newRotator(d));
//        return this;
//    }
//
//    public  addFilter(ImageFilter imagefilter)
//    {
//        if(imagefilter == null)
//        {
//            throw new NullPointerException("Filter is null.");
//        } else
//        {
//            filterPipeline.add(imagefilter);
//            return this;
//        }
//    }
//
//    public  addFilters(java.util.List list)
//    {
//        if(list == null)
//        {
//            throw new NullPointerException("Filters is null.");
//        } else
//        {
//            filterPipeline.addAll(list);
//            return this;
//        }
//    }
//
//    private void checkReadiness()
//    {
//        for(Iterator iterator = statusMap.entrySet().iterator(); iterator.hasNext();)
//        {
//            java.util.mbnails.Builder builder = (java.util.mbnails.Builder.statusMap)iterator.next();
//            if(builder.tusMap() == Status.NOT_READY)
//                throw new IllegalStateException((new StringBuilder()).append(((Properties)builder.Properties()).getName()).append(" is not set.").toString());
//        }
//
//    }
//
//    private Resizer makeResizer()
//    {
//        if(statusMap.get(Properties.SCALING_MODE) == Status.ALREADY_SET)
//            return makeResizer(scalingMode);
//        else
//            return resizer;
//    }
//
//    private Resizer makeResizer(ScalingMode scalingmode)
//    {
//        HashMap hashmap = new HashMap();
//        hashmap.put(RenderingHints.KEY_ALPHA_INTERPOLATION, alphaInterpolation.getValue());
//        hashmap.put(RenderingHints.KEY_DITHERING, dithering.getValue());
//        hashmap.put(RenderingHints.KEY_ANTIALIASING, antialiasing.getValue());
//        hashmap.put(RenderingHints.KEY_RENDERING, rendering.getValue());
//        if(scalingmode == ScalingMode.BILINEAR)
//            return new BilinearResizer(hashmap);
//        if(scalingmode == ScalingMode.BICUBIC)
//            return new BicubicResizer(hashmap);
//        if(scalingmode == ScalingMode.PROGRESSIVE_BILINEAR)
//            return new ProgressiveBilinearResizer(hashmap);
//        else
//            return new ProgressiveBilinearResizer(hashmap);
//    }
//
//    private ThumbnailParameter makeParam()
//    {
//        Resizer resizer1 = makeResizer();
//        int i = imageType;
//        if(imageType == IMAGE_TYPE_UNSPECIFIED)
//            i = -1;
//        if(Double.isNaN(scaleWidth))
//        {
//            if(width == -1 && height == -1)
//                throw new IllegalStateException("The width or height must be specified. If this exception is thrown, it is due to a bug in the Thumbnailator library.");
//            if(width == -1)
//                width = 0x7fffffff;
//            if(height == -1)
//                height = 0x7fffffff;
//            return new ThumbnailParameter(new Dimension(width, height), sourceRegion, keepAspectRatio, outputFormat, outputFormatType, outputQuality, i, filterPipeline.getFilters(), resizer1);
//        } else
//        {
//            return new ThumbnailParameter(scaleWidth, scaleHeight, sourceRegion, keepAspectRatio, outputFormat, outputFormatType, outputQuality, i, filterPipeline.getFilters(), resizer1);
//        }
//    }
//
//    public Iterable iterableBufferedImages()
//    {
//        checkReadiness();
//        return new BufferedImageIterable(null);
//    }
//
//    public java.util.List asBufferedImages()
//        throws IOException
//    {
//        checkReadiness();
//        ArrayList arraylist = new ArrayList();
//        BufferedImageSink bufferedimagesink;
//        for(Iterator iterator = sources.iterator(); iterator.hasNext(); arraylist.add(bufferedimagesink.getSink()))
//        {
//            ImageSource imagesource = (ImageSource)iterator.next();
//            bufferedimagesink = new BufferedImageSink();
//            Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(makeParam(), imagesource, bufferedimagesink));
//        }
//
//        return arraylist;
//    }
//
//    public BufferedImage asBufferedImage()
//        throws IOException
//    {
//        checkReadiness();
//        Iterator iterator = sources.iterator();
//        ImageSource imagesource = (ImageSource)iterator.next();
//        if(iterator.hasNext())
//        {
//            throw new IllegalArgumentException("Cannot create one thumbnail from multiple original images.");
//        } else
//        {
//            BufferedImageSink bufferedimagesink = new BufferedImageSink();
//            Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(makeParam(), imagesource, bufferedimagesink));
//            return bufferedimagesink.getSink();
//        }
//    }
//
//    public java.util.List asFiles(Iterable iterable)
//        throws IOException
//    {
//        checkReadiness();
//        if(iterable == null)
//            throw new NullPointerException("File name iterable is null.");
//        ArrayList arraylist = new ArrayList();
//        ThumbnailParameter thumbnailparameter = makeParam();
//        Iterator iterator = iterable.iterator();
//        for(Iterator iterator1 = sources.iterator(); iterator1.hasNext();)
//        {
//            ImageSource imagesource = (ImageSource)iterator1.next();
//            if(!iterator.hasNext())
//                throw new IndexOutOfBoundsException("Not enough file names provided by iterator.");
//            FileImageSink fileimagesink = new FileImageSink((File)iterator.next(), allowOverwrite);
//            try
//            {
//                Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(thumbnailparameter, imagesource, fileimagesink));
//                arraylist.add(fileimagesink.getSink());
//            }
//            catch(IllegalArgumentException illegalargumentexception) { }
//        }
//
//        return arraylist;
//    }
//
//    public void toFiles(Iterable iterable)
//        throws IOException
//    {
//        asFiles(iterable);
//    }
//
//    public java.util.List asFiles(Rename rename)
//        throws IOException
//    {
//        checkReadiness();
//        if(rename == null)
//            throw new NullPointerException("Rename is null.");
//        ArrayList arraylist = new ArrayList();
//        ThumbnailParameter thumbnailparameter = makeParam();
//        for(Iterator iterator = sources.iterator(); iterator.hasNext();)
//        {
//            ImageSource imagesource = (ImageSource)iterator.next();
//            if(!(imagesource instanceof FileImageSource))
//                throw new IllegalStateException("Cannot create thumbnails to files if original images are not from files.");
//            File file = ((FileImageSource)imagesource).getSource();
//            File file1 = new File(file.getParent(), rename.apply(file.getName()));
//            FileImageSink fileimagesink = new FileImageSink(file1, allowOverwrite);
//            try
//            {
//                Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(thumbnailparameter, imagesource, fileimagesink));
//                arraylist.add(fileimagesink.getSink());
//            }
//            catch(IllegalArgumentException illegalargumentexception) { }
//        }
//
//        return arraylist;
//    }
//
//    public void toFiles(Rename rename)
//        throws IOException
//    {
//        asFiles(rename);
//    }
//
//    public void toFile(File file)
//        throws IOException
//    {
//        checkReadiness();
//        Iterator iterator = sources.iterator();
//        ImageSource imagesource = (ImageSource)iterator.next();
//        if(iterator.hasNext())
//        {
//            throw new IllegalArgumentException("Cannot output multiple thumbnails to one file.");
//        } else
//        {
//            FileImageSink fileimagesink = new FileImageSink(file, allowOverwrite);
//            Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(makeParam(), imagesource, fileimagesink));
//            return;
//        }
//    }
//
//    public void toFile(String s)
//        throws IOException
//    {
//        checkReadiness();
//        Iterator iterator = sources.iterator();
//        ImageSource imagesource = (ImageSource)iterator.next();
//        if(iterator.hasNext())
//        {
//            throw new IllegalArgumentException("Cannot output multiple thumbnails to one file.");
//        } else
//        {
//            FileImageSink fileimagesink = new FileImageSink(s, allowOverwrite);
//            Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(makeParam(), imagesource, fileimagesink));
//            return;
//        }
//    }
//
//    public void toOutputStream(OutputStream outputstream)
//        throws IOException
//    {
//        checkReadiness();
//        Iterator iterator = sources.iterator();
//        ImageSource imagesource = (ImageSource)iterator.next();
//        if(iterator.hasNext())
//            throw new IllegalArgumentException("Cannot output multiple thumbnails to a single OutputStream.");
//        if((imagesource instanceof BufferedImageSource) && outputFormat == ThumbnailParameter.ORIGINAL_FORMAT)
//        {
//            throw new IllegalStateException("Output format not specified.");
//        } else
//        {
//            OutputStreamImageSink outputstreamimagesink = new OutputStreamImageSink(outputstream);
//            Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(makeParam(), imagesource, outputstreamimagesink));
//            return;
//        }
//    }
//
//    public void toOutputStreams(Iterable iterable)
//        throws IOException
//    {
//        checkReadiness();
//        if(iterable == null)
//            throw new NullPointerException("OutputStream iterable is null.");
//        Iterator iterator = iterable.iterator();
//        ImageSource imagesource;
//        OutputStreamImageSink outputstreamimagesink;
//        for(Iterator iterator1 = sources.iterator(); iterator1.hasNext(); Thumbnailator.createThumbnail(new SourceSinkThumbnailTask(makeParam(), imagesource, outputstreamimagesink)))
//        {
//            imagesource = (ImageSource)iterator1.next();
//            if((imagesource instanceof BufferedImageSource) && outputFormat == ThumbnailParameter.ORIGINAL_FORMAT)
//                throw new IllegalStateException("Output format not specified.");
//            if(!iterator.hasNext())
//                throw new IndexOutOfBoundsException("Not enough file names provided by iterator.");
//            outputstreamimagesink = new OutputStreamImageSink((OutputStream)iterator.next());
//        }
//
//    }
//
//    private final Iterable sources;
//    private final Map statusMap = new HashMap();
//    private static int IMAGE_TYPE_UNSPECIFIED = -1;
//    private static final int DIMENSION_NOT_SPECIFIED = -1;
//    private int width;
//    private int height;
//    private double scaleWidth;
//    private double scaleHeight;
//    private Region sourceRegion;
//    private int imageType;
//    private boolean keepAspectRatio;
//    private String outputFormat;
//    private String outputFormatType;
//    private float outputQuality;
//    private ScalingMode scalingMode;
//    private AlphaInterpolation alphaInterpolation;
//    private Dithering dithering;
//    private Antialiasing antialiasing;
//    private Rendering rendering;
//    private Resizer resizer;
//    private boolean allowOverwrite;
//    private Pipeline filterPipeline;
//
//
//
//
//
//
//
//
//
//    private StringImageSourceIterator(Iterable iterable)
//    {
//        statusMap.put(Properties.SIZE, Status.NOT_READY);
//        statusMap.put(Properties.WIDTH, Status.OPTIONAL);
//        statusMap.put(Properties.HEIGHT, Status.OPTIONAL);
//        statusMap.put(Properties.SCALE, Status.NOT_READY);
//        statusMap.put(Properties.SOURCE_REGION, Status.OPTIONAL);
//        statusMap.put(Properties.IMAGE_TYPE, Status.OPTIONAL);
//        statusMap.put(Properties.SCALING_MODE, Status.OPTIONAL);
//        statusMap.put(Properties.ALPHA_INTERPOLATION, Status.OPTIONAL);
//        statusMap.put(Properties.ANTIALIASING, Status.OPTIONAL);
//        statusMap.put(Properties.DITHERING, Status.OPTIONAL);
//        statusMap.put(Properties.RENDERING, Status.OPTIONAL);
//        statusMap.put(Properties.KEEP_ASPECT_RATIO, Status.OPTIONAL);
//        statusMap.put(Properties.OUTPUT_FORMAT, Status.OPTIONAL);
//        statusMap.put(Properties.OUTPUT_FORMAT_TYPE, Status.OPTIONAL);
//        statusMap.put(Properties.OUTPUT_QUALITY, Status.OPTIONAL);
//        statusMap.put(Properties.RESIZER, Status.OPTIONAL);
//        statusMap.put(Properties.ALLOW_OVERWRITE, Status.OPTIONAL);
//        width = -1;
//        height = -1;
//        scaleWidth = (0.0D / 0.0D);
//        scaleHeight = (0.0D / 0.0D);
//        imageType = IMAGE_TYPE_UNSPECIFIED;
//        keepAspectRatio = true;
//        outputFormat = ThumbnailParameter.ORIGINAL_FORMAT;
//        outputFormatType = ThumbnailParameter.DEFAULT_FORMAT_TYPE;
//        outputQuality = (0.0F / 0.0F);
//        scalingMode = ScalingMode.PROGRESSIVE_BILINEAR;
//        alphaInterpolation = AlphaInterpolation.DEFAULT;
//        dithering = Dithering.DEFAULT;
//        antialiasing = Antialiasing.DEFAULT;
//        rendering = Rendering.DEFAULT;
//        resizer = Resizers.PROGRESSIVE;
//        allowOverwrite = true;
//        filterPipeline = new Pipeline();
//        sources = iterable;
//        statusMap.put(Properties.OUTPUT_FORMAT, Status.OPTIONAL);
//    }
//}