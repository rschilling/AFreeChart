/*   1:    */ package org.afree.graphics.geom;
/*   2:    */ 
/*   3:    */ import android.graphics.Canvas;
/*   4:    */ import android.graphics.Paint;
/*   5:    */ import android.graphics.Paint.Style;
/*   6:    */ import android.graphics.Path;
/*   7:    */ import android.graphics.Path.Direction;
/*   8:    */ import android.graphics.PointF;
/*   9:    */ import android.graphics.Rect;
/*  10:    */ import android.graphics.RectF;
/*  11:    */ import android.graphics.Region;
/*  12:    */ import android.graphics.Region.Op;
/*  13:    */ 
/*  14:    */ public class OvalShape
/*  15:    */   implements Shape
/*  16:    */ {
/*  17:    */   private RectF mOval;
/*  18:    */   private Path mPath;
/*  19:    */   private Region mRegion;
/*  20:    */   
/*  21:    */   public OvalShape() {}
/*  22:    */   
/*  23:    */   public OvalShape(float x, float y, float width, float height)
/*  24:    */   {
/*  25: 55 */     this.mOval = new RectF(x, y, x + width, y + height);
/*  26: 56 */     this.mPath = new Path();
/*  27: 57 */     update();
/*  28:    */   }
/*  29:    */   
/*  30:    */   public OvalShape(double x, double y, double width, double height)
/*  31:    */   {
/*  32: 68 */     this.mOval = new RectF((float)x, (float)y, (float)(x + width), (float)(y + height));
/*  33: 69 */     this.mPath = new Path();
/*  34: 70 */     update();
/*  35:    */   }
/*  36:    */   
/*  37:    */   public OvalShape(OvalShape ovalShape)
/*  38:    */   {
/*  39: 78 */     this(ovalShape.getX(), ovalShape.getY(), ovalShape.getWidth(), ovalShape.getHeight());
/*  40: 79 */     this.mPath = ovalShape.mPath;
/*  41: 80 */     this.mRegion = ovalShape.mRegion;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public Path getPath()
/*  45:    */   {
/*  46: 87 */     Path path = new Path();
/*  47: 88 */     path.addOval(this.mOval, Path.Direction.CW);
/*  48: 89 */     return path;
/*  49:    */   }
/*  50:    */   
/*  51:    */   public void draw(Canvas canvas, Paint paint)
/*  52:    */   {
/*  53: 96 */     paint.setStyle(Paint.Style.STROKE);
/*  54: 97 */     canvas.drawOval(this.mOval, paint);
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void fill(Canvas canvas, Paint paint)
/*  58:    */   {
/*  59:105 */     paint.setStyle(Paint.Style.FILL);
/*  60:106 */     canvas.drawOval(this.mOval, paint);
/*  61:    */   }
/*  62:    */   
/*  63:    */   public void fillAndStroke(Canvas canvas, Paint paint)
/*  64:    */   {
/*  65:114 */     paint.setStyle(Paint.Style.FILL_AND_STROKE);
/*  66:115 */     canvas.drawOval(this.mOval, paint);
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void clip(Canvas canvas)
/*  70:    */   {
/*  71:123 */     canvas.clipPath(this.mPath);
/*  72:    */   }
/*  73:    */   
/*  74:    */   public boolean contains(RectShape rect)
/*  75:    */   {
/*  76:131 */     if ((this.mRegion.contains((int)rect.getMinX(), (int)rect.getMinY())) && 
/*  77:132 */       (this.mRegion.contains((int)rect.getMinX(), (int)rect.getMaxY())) && 
/*  78:133 */       (this.mRegion.contains((int)rect.getMaxX(), (int)rect.getMinY())) && 
/*  79:134 */       (this.mRegion.contains((int)rect.getMaxX(), (int)rect.getMaxY()))) {
/*  80:136 */       return true;
/*  81:    */     }
/*  82:138 */     return false;
/*  83:    */   }
/*  84:    */   
/*  85:    */   public boolean contains(float x, float y)
/*  86:    */   {
/*  87:145 */     return this.mRegion.contains((int)x, (int)y);
/*  88:    */   }
/*  89:    */   
/*  90:    */   public boolean contains(float x, float y, float width, float height)
/*  91:    */   {
/*  92:152 */     if ((this.mRegion.contains((int)x, (int)y)) && 
/*  93:153 */       (this.mRegion.contains((int)x, (int)(y + height))) && 
/*  94:154 */       (this.mRegion.contains((int)(x + width), (int)y)) && 
/*  95:155 */       (this.mRegion.contains((int)(x + width), (int)(y + height)))) {
/*  96:157 */       return true;
/*  97:    */     }
/*  98:159 */     return false;
/*  99:    */   }
/* 100:    */   
/* 101:    */   public boolean contains(PointF point)
/* 102:    */   {
/* 103:166 */     return this.mRegion.contains((int)point.x, (int)point.y);
/* 104:    */   }
/* 105:    */   
/* 106:    */   @Deprecated
/* 107:    */   public RectShape getBounds()
/* 108:    */   {
/* 109:174 */     return new RectShape(this.mRegion.getBounds());
/* 110:    */   }
/* 111:    */   
/* 112:    */   public void getBounds(RectShape rect)
/* 113:    */   {
/* 114:181 */     rect.setRect(this.mRegion.getBounds());
/* 115:    */   }
/* 116:    */   
/* 117:    */   public boolean intersects(float x, float y, float width, float height)
/* 118:    */   {
/* 119:188 */     Region region = new Region(this.mRegion);
/* 120:    */     float tmpw;
/* 121:    */     float tmpx;
/* 122:    */     
/* 123:191 */     if (x < width + x)
/* 124:    */     {
/* 125:192 */       tmpx = x;
/* 126:193 */       tmpw = width;
/* 127:    */     }
/* 128:    */     else
/* 129:    */     {
/* 130:196 */       tmpx = width + x;
/* 131:197 */       tmpw = Math.abs(width);
/* 132:    */     }
/* 133:    */     float tmph;
/* 134:    */     float tmpy;
/* 135:    */
/* 136:199 */     if (y < height + y)
/* 137:    */     {
/* 138:200 */       tmpy = y;
/* 139:201 */       tmph = height;
/* 140:    */     }
/* 141:    */     else
/* 142:    */     {
/* 143:204 */       tmpy = height + y;
/* 144:205 */       tmph = Math.abs(height);
/* 145:    */     }
/* 146:207 */     return region.op((int)tmpx, (int)tmpy, (int)(tmpx + tmpw), (int)(tmpy + tmph), Region.Op.INTERSECT);
/* 147:    */   }
/* 148:    */   
/* 149:    */   public boolean intersects(Rect rect)
/* 150:    */   {
/* 151:214 */     return intersects(rect.left, rect.top, rect.right - rect.left, rect.bottom - rect.top);
/* 152:    */   }
/* 153:    */   
/* 154:    */   public boolean intersects(RectShape rect)
/* 155:    */   {
/* 156:221 */     return intersects(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
/* 157:    */   }
/* 158:    */   
/* 159:    */   public void translate(float x, float y)
/* 160:    */   {
/* 161:228 */     this.mOval.left += x;
/* 162:229 */     this.mOval.top += y;
/* 163:230 */     this.mOval.right += x;
/* 164:231 */     this.mOval.bottom += y;
/* 165:232 */     update();
/* 166:    */   }
/* 167:    */   
/* 168:    */   public float getX()
/* 169:    */   {
/* 170:239 */     return this.mOval.left;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public float getY()
/* 174:    */   {
/* 175:247 */     return this.mOval.top;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public float getWidth()
/* 179:    */   {
/* 180:255 */     return this.mOval.width();
/* 181:    */   }
/* 182:    */   
/* 183:    */   public float getHeight()
/* 184:    */   {
/* 185:263 */     return this.mOval.height();
/* 186:    */   }
/* 187:    */   
/* 188:    */   public float getMinX()
/* 189:    */   {
/* 190:271 */     return getX();
/* 191:    */   }
/* 192:    */   
/* 193:    */   public float getMaxX()
/* 194:    */   {
/* 195:279 */     return this.mOval.right;
/* 196:    */   }
/* 197:    */   
/* 198:    */   public float getCenterX()
/* 199:    */   {
/* 200:287 */     return this.mOval.centerX();
/* 201:    */   }
/* 202:    */   
/* 203:    */   public float getMinY()
/* 204:    */   {
/* 205:295 */     return getY();
/* 206:    */   }
/* 207:    */   
/* 208:    */   public float getMaxY()
/* 209:    */   {
/* 210:303 */     return this.mOval.bottom;
/* 211:    */   }
/* 212:    */   
/* 213:    */   public float getCenterY()
/* 214:    */   {
/* 215:311 */     return this.mOval.centerY();
/* 216:    */   }
/* 217:    */   
/* 218:    */   private void update()
/* 219:    */   {
/* 220:318 */     this.mPath.reset();
/* 221:319 */     this.mPath.addOval(this.mOval, Path.Direction.CW);
/* 222:320 */     this.mRegion = new Region((int)this.mOval.left, (int)this.mOval.top, (int)this.mOval.right, (int)this.mOval.bottom);
/* 223:321 */     this.mRegion.setPath(this.mPath, this.mRegion);
/* 224:    */   }
/* 225:    */   
/* 226:    */   public boolean equals(Object o)
/* 227:    */   {
/* 228:330 */     if ((o instanceof OvalShape))
/* 229:    */     {
/* 230:331 */       OvalShape ovalShape = (OvalShape)o;
/* 231:332 */       if ((this.mOval.left == ovalShape.mOval.left) && 
/* 232:333 */         (this.mOval.right == ovalShape.mOval.right) && 
/* 233:334 */         (this.mOval.top == ovalShape.mOval.top) && 
/* 234:335 */         (this.mOval.bottom == ovalShape.mOval.bottom)) {
/* 235:336 */         return true;
/* 236:    */       }
/* 237:    */     }
/* 238:339 */     return false;
/* 239:    */   }
/* 240:    */   
/* 241:    */   public Shape clone()
/* 242:    */   {
/* 243:348 */     return new OvalShape(this);
/* 244:    */   }
/* 245:    */ }


/* Location:           J:\Ioffice\doc\xxprojects\2014_Projects\BW121\afreegraphics.jar
 * Qualified Name:     org.afree.graphics.geom.OvalShape
 * JD-Core Version:    0.7.0.1
 */