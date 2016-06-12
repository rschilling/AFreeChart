/*   1:    */ package org.afree.graphics.geom;
/*   2:    */ 
/*   3:    */ import android.graphics.Canvas;
/*   4:    */ import android.graphics.Matrix;
/*   5:    */ import android.graphics.Paint;
/*   6:    */ import android.graphics.Paint.Style;
/*   7:    */ import android.graphics.Path;
/*   8:    */ import android.graphics.PointF;
/*   9:    */ import android.graphics.Rect;
/*  10:    */ import android.graphics.RectF;
/*  11:    */ import android.graphics.Region;
/*  12:    */ import android.graphics.Region.Op;
/*  13:    */ import android.graphics.RegionIterator;
/*  14:    */ 
/*  15:    */ public class PathShape
/*  16:    */   implements Shape
/*  17:    */ {
/*  18:    */   private Path mPath;
/*  19:    */   private Region mRegion;
/*  20:    */   
/*  21:    */   public PathShape()
/*  22:    */   {
/*  23: 43 */     this(new Path());
/*  24:    */   }
/*  25:    */   
/*  26:    */   public PathShape(Path path)
/*  27:    */   {
/*  28: 51 */     this.mPath = path;
/*  29: 52 */     update();
/*  30:    */   }
/*  31:    */   
/*  32:    */   public PathShape(PathShape pathShape)
/*  33:    */   {
/*  34: 60 */     this(new Path(pathShape.getPath()));
/*  35:    */   }
/*  36:    */   
/*  37:    */   public Path getPath()
/*  38:    */   {
/*  39: 67 */     return this.mPath;
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void setPath(Path path)
/*  43:    */   {
/*  44: 75 */     this.mPath = path;
/*  45: 76 */     update();
/*  46:    */   }
/*  47:    */   
/*  48:    */   public void draw(Canvas canvas, Paint paint)
/*  49:    */   {
/*  50: 83 */     paint.setStyle(Paint.Style.STROKE);
/*  51: 84 */     canvas.drawPath(this.mPath, paint);
/*  52:    */   }
/*  53:    */   
/*  54:    */   public void fill(Canvas canvas, Paint paint)
/*  55:    */   {
/*  56: 91 */     paint.setStyle(Paint.Style.FILL);
/*  57: 92 */     canvas.drawPath(this.mPath, paint);
/*  58:    */   }
/*  59:    */   
/*  60:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/*  61:    */   {
/*  62: 99 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/*  63:100 */     canvas.drawPath(this.mPath, paint);
/*  64:    */   }
/*  65:    */   
/*  66:    */   public void clip(Canvas canvas)
/*  67:    */   {
/*  68:107 */     canvas.clipPath(this.mPath);
/*  69:108 */     update();
/*  70:    */   }
/*  71:    */   
/*  72:    */   public boolean contains(RectShape rect)
/*  73:    */   {
/*  74:115 */     Region tmpRegion = new Region();
/*  75:116 */     Region targetRegion = new Region((int)rect.getX(), (int)rect.getY(), 
/*  76:117 */       (int)(rect.getWidth() + rect.getX()), (int)(rect.getHeight() + rect.getY()));
/*  77:118 */     tmpRegion.op(this.mRegion, targetRegion, Region.Op.INTERSECT);
/*  78:119 */     return !targetRegion.op(tmpRegion, Region.Op.XOR);
/*  79:    */   }
/*  80:    */   
/*  81:    */   public boolean contains(float x, float y)
/*  82:    */   {
/*  83:126 */     return this.mRegion.contains((int)x, (int)y);
/*  84:    */   }
/*  85:    */   
/*  86:    */   public boolean contains(float x, float y, float width, float height)
/*  87:    */   {
/*  88:133 */     Region tmpRegion = new Region();
/*  89:134 */     Region targetRegion = new Region((int)x, (int)y, (int)(width + x), (int)(height + y));
/*  90:135 */     tmpRegion.op(this.mRegion, targetRegion, Region.Op.INTERSECT);
/*  91:136 */     return !targetRegion.op(tmpRegion, Region.Op.XOR);
/*  92:    */   }
/*  93:    */   
/*  94:    */   public boolean contains(PointF point)
/*  95:    */   {
/*  96:143 */     return this.mRegion.contains((int)point.x, (int)point.y);
/*  97:    */   }
/*  98:    */   
/*  99:    */   @Deprecated
/* 100:    */   public RectShape getBounds()
/* 101:    */   {
/* 102:151 */     return new RectShape(this.mRegion.getBounds());
/* 103:    */   }
/* 104:    */   
/* 105:    */   public void getBounds(RectShape rect)
/* 106:    */   {
/* 107:158 */     rect.setRect(this.mRegion.getBounds());
/* 108:    */   }
/* 109:    */   
/* 110:    */   public boolean intersects(float x, float y, float width, float height)
/* 111:    */   {
/* 112:165 */     if (this.mRegion.isEmpty()) {
/* 113:166 */       return false;
/* 114:    */     }
/* 115:168 */     Rect rect = new Rect();
/* 116:169 */     RegionIterator iterator = new RegionIterator(this.mRegion);
/* 117:170 */     Region region = new Region(this.mRegion);
/* 118:173 */     if (iterator.next(rect))
/* 119:    */     {
/* 120:    */       float tmpw;
/* 121:    */       float tmpx;
/* 122:    */       
/* 123:174 */       if (x < width + x)
/* 124:    */       {
/* 125:175 */         tmpx = x;
/* 126:176 */         tmpw = width;
/* 127:    */       }
/* 128:    */       else
/* 129:    */       {
/* 130:179 */         tmpx = width + x;
/* 131:180 */         tmpw = Math.abs(width);
/* 132:    */       }
/* 133:    */       float tmph;
/* 134:    */       float tmpy;
/* 135:    */  
/* 136:182 */       if (y < height + y)
/* 137:    */       {
/* 138:183 */         tmpy = y;
/* 139:184 */         tmph = height;
/* 140:    */       }
/* 141:    */       else
/* 142:    */       {
/* 143:187 */         tmpy = height + y;
/* 144:188 */         tmph = Math.abs(height);
/* 145:    */       }
/* 146:190 */       return region.op((int)tmpx, (int)tmpy, (int)(tmpx + tmpw), (int)(tmpy + tmph), Region.Op.INTERSECT);
/* 147:    */     }
/* 148:192 */     return false;
/* 149:    */   }
/* 150:    */   
/* 151:    */   public boolean intersects(Rect rect)
/* 152:    */   {
/* 153:199 */     return intersects(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/* 154:    */   }
/* 155:    */   
/* 156:    */   public boolean intersects(RectShape rect)
/* 157:    */   {
/* 158:206 */     return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 159:    */   }
/* 160:    */   
/* 161:    */   public void translate(float x, float y)
/* 162:    */   {
/* 163:213 */     Matrix mat = new Matrix();
/* 164:214 */     mat.postTranslate(x, y);
/* 165:215 */     this.mPath.transform(mat);
/* 166:216 */     update();
/* 167:    */   }
/* 168:    */   
/* 169:    */   public void moveTo(float x, float y)
/* 170:    */   {
/* 171:225 */     this.mPath.moveTo(x, y);
/* 172:226 */     update();
/* 173:    */   }
/* 174:    */   
/* 175:    */   public void lineTo(float x, float y)
/* 176:    */   {
/* 177:235 */     this.mPath.lineTo(x, y);
/* 178:236 */     update();
/* 179:    */   }
/* 180:    */   
/* 181:    */   public void append(Shape shape)
/* 182:    */   {
/* 183:244 */     this.mPath.addPath(shape.getPath());
/* 184:245 */     update();
/* 185:    */   }
/* 186:    */   
/* 187:    */   public void closePath()
/* 188:    */   {
/* 189:252 */     this.mPath.close();
/* 190:253 */     update();
/* 191:    */   }
/* 192:    */   
/* 193:    */   public void reset()
/* 194:    */   {
/* 195:260 */     this.mPath.reset();
/* 196:261 */     update();
/* 197:    */   }
/* 198:    */   
/* 199:    */   public float getX()
/* 200:    */   {
/* 201:269 */     return this.mRegion.getBounds().left;
/* 202:    */   }
/* 203:    */   
/* 204:    */   public float getY()
/* 205:    */   {
/* 206:277 */     return this.mRegion.getBounds().top;
/* 207:    */   }
/* 208:    */   
/* 209:    */   public float getWidth()
/* 210:    */   {
/* 211:285 */     return this.mRegion.getBounds().width();
/* 212:    */   }
/* 213:    */   
/* 214:    */   public float getHeight()
/* 215:    */   {
/* 216:293 */     return this.mRegion.getBounds().height();
/* 217:    */   }
/* 218:    */   
/* 219:    */   private void update()
/* 220:    */   {
/* 221:300 */     RectF rect = new RectF();
/* 222:301 */     this.mPath.computeBounds(rect, false);
/* 223:302 */     this.mRegion = new Region((int)rect.left, (int)rect.top, (int)rect.right, (int)rect.bottom);
/* 224:303 */     this.mRegion.setPath(this.mPath, this.mRegion);
/* 225:    */   }
/* 226:    */   
/* 227:    */   public boolean equals(Object o)
/* 228:    */   {
/* 229:311 */     if ((o instanceof PathShape))
/* 230:    */     {
/* 231:312 */       PathShape pathShape = (PathShape)o;
/* 232:313 */       Region region = new Region(this.mRegion);
/* 233:314 */       if (!region.op(pathShape.mRegion, Region.Op.XOR)) {
/* 234:315 */         return true;
/* 235:    */       }
/* 236:    */     }
/* 237:318 */     return false;
/* 238:    */   }
/* 239:    */   
/* 240:    */   public Shape clone()
/* 241:    */   {
/* 242:326 */     return new PathShape(this);
/* 243:    */   }
/* 244:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.PathShape
 * JD-Core Version:    0.7.0.1
 */