package com.atguigu.huffmancode;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Data;
import lombok.ToString;
import org.junit.jupiter.api.Test;

/**
 * 哈夫曼编码的应用
 * 1.数据压缩
 * 2.数据解压
 */
public class HuffmanCode {


  //测试创建哈夫曼树
  @Test
  public void test1() {
    int[] arrs = {13, 7, 8, 3, 29, 6, 1};
    List list = new ArrayList();
    for (int arr : arrs) {
      list.add(new Node(null, arr));
    }
    Node huffmanTree = createHuffmanTree(list);
    //遍历
    huffmanTree.preOrder();

  }


  //测试哈夫曼编码表
  @Test
  public void test2() {
    String content = "i like like like java do you like a java";
    byte[] contentBytes = content.getBytes();
    //[105, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 108, 105, 107, 101, 32, 106, 97, 118, 97, 32, 100, 111, 32, 121, 111, 117, 32, 108, 105, 107, 101, 32, 97, 32, 106, 97, 118, 97]
    System.out.println(Arrays.toString(contentBytes));//40
    List<Node> nodes = getNodes(contentBytes);
    Node huffmanTree = createHuffmanTree(nodes);
    Map codes = getHuffmanCodes(huffmanTree);
    System.out.println(codes);
  }

  //测试 zipToByte 方法
  @Test
  public void test3() {
    String content = "i like like like java do you like a java";
    byte[] contentBytes = content.getBytes();
    List<Node> nodes = getNodes(contentBytes);
    Node huffmanTree = createHuffmanTree(nodes);
    Map huffmanCodes = getHuffmanCodes(huffmanTree);
    byte[] zipByte = zipToByte(contentBytes, huffmanCodes);
    System.out.println(Arrays.toString(zipByte));
  }

  //测试 zipFile方法 压缩文件
  @Test
  public void test4() {
    zipFile("E:\\pushfiles\\哎呀行了行了.jpg",
        "E:\\pushfiles\\1.zip");
    System.out.println("压缩完成");
  }

  //测试unzipFile方法
  @Test
  public void test5() {
    unzipFile("E:\\pushfiles\\1.zip",
        "E:\\pushfiles\\哈哈.jpg");
  }

  /**
   * 通过哈夫曼编码表 解压文件
   * @param srcFile 要解压的文件
   * @param dstFile 解压的路径
   */
  public static void unzipFile(String srcFile, String dstFile) {
    //定义流
    InputStream is = null;
    ObjectInputStream ois = null;
    OutputStream os = null;

    try {
      //创建文件输入流
      is = new FileInputStream(srcFile);
      //创建一个和文件输入流关联的ObjectInputStream
      ois = new ObjectInputStream(is);
      //读取byte数组 huffmanBytes
      byte[] huffmanBytes = (byte[]) ois.readObject();
      //读取哈夫曼编码表
      Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

      //解码
      byte[] bytes = unzip(huffmanCodes, huffmanBytes);

      //将bytes写入目标文件
      os = new FileOutputStream(dstFile);
      os.write(bytes);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        os.close();
        ois.close();
        is.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }

  /**
   * 完成数据的解压
   * 举例：以之前的String为例
   * 1.将哈夫曼bytes数组先恢复成字符串
   * [-88, -65, -56, -65, -56, -65, -55, 77, -57, 6, -24, -14, -117, -4, -60, -90, 28]
   *  恢复成==>
   * "1010100010111"
   * 2.再将字符串 通过哈夫曼编码表 恢复成 该原始文件对应的bytes
   * @param huffmanCodes 哈夫曼编码表
   * @param huffmanBytes 哈夫曼bytes数组
   * @return
   */
  private static byte[] unzip(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
    StringBuilder sb = new StringBuilder();
    //得到哈夫曼bytes对应的二进制字符串，形如："1010100010111..."
    for (int i = 0; i < huffmanBytes.length; i++) {
      byte b = huffmanBytes[i];
      //判断是否是最后一个字节
      boolean flag = (i == huffmanBytes.length - 1);
      sb.append(byteToBitString(!flag, b));
    }
    //把字符串按照哈夫曼编码表进行恢复
    //先将哈夫曼编码表的key和value进行调换一下，因为要反向查询
    Map<String, Byte> map = new HashMap<>();
    for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
      map.put(entry.getValue(), entry.getKey());
    }
    List<Byte> list = new ArrayList<>();
    for (int i = 0; i < sb.length(); ) {
      int count = 1;
      boolean flag = true;
      Byte b = null;
      //开始一个一个匹配
      while (flag) {
        String key = sb.substring(i, i + count);
        b = map.get(key);
        if (b == null) {
          //没有匹配到
          count++;
        } else {
          //匹配到
          flag = false;
        }
      }
      //添加到集合中
      list.add(b);
      //让 i 从 i+count 再次进行循环匹配
      i += count;
    }
    //当循环结束后，list 就存放了对应的byte 再把list中的数据放入byte[] 并返回
    byte[] b = new byte[list.size()];
    for (int i = 0; i < b.length; i++) {
      b[i] = list.get(i);
    }
    return b;
  }

  /**
   * 讲一个byte转成一个二进制的字符串
   * @param flag 标志是否需要补高位 （如果是最后一个字节，无需补高位）
   * @param b 传入的byte
   * @return 返回该 b 对应的二进制的字符串（按补码返回）
   */
  private static String byteToBitString(boolean flag, byte b) {
    //将b转成int
    int temp = b;
    //如果是正数需要补高位
    if (flag) {
      temp |= 256;//按位于256
      // 1 0000 0000 | 0000 0001 => 1 0000 0001
    }
    //返回的就是temp对应的二进制的补码
    String str = Integer.toBinaryString(temp);
    if (flag) {
      return str.substring(str.length() - 8);
    } else {
      return str;
    }
  }

  /**
   * 通过哈夫曼编码压缩文件
   * @param srcFile 目标要压缩的文件
   * @param dstFile 压缩好的文件的存放位置
   */
  public static void zipFile(String srcFile, String dstFile) {
    //创建流
    OutputStream os = null;
    ObjectOutputStream oos = null;
    FileInputStream fis = null;
    try {
      //将文件变成输入流
      fis = new FileInputStream(srcFile);
      //创建一个和源文件大小一样的byte[]
      byte[] b = new byte[fis.available()];
      //读取文件
      fis.read(b);

      //直接压缩
      byte[] huffmanBytes = huffmanZip(b);

      //创建文件输出流，存放压缩文件
      os = new FileOutputStream(dstFile);
      //创建一个和文件输出流关联的ObjectOutputStream
      oos = new ObjectOutputStream(os);
      //把哈夫曼编码后的字节写入文件
      oos.writeObject(huffmanBytes);
      //把哈夫曼编码表也必须要写入文件，需要后序解压使用
      oos.writeObject(huffmanCodes);
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } finally {
      try {
        fis.close();
        oos.close();
        os.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  }


  /**
   * 哈夫曼压缩方法
   * @param contentBytes 字符串的bytes数组
   * @return 返回哈夫曼编码表对应的bytes数组
   */
  private static byte[] huffmanZip(byte[] contentBytes) {
    //把contentBytes变成Node节点
    List<Node> nodes = getNodes(contentBytes);
    //创建哈夫曼树
    Node huffmanTree = createHuffmanTree(nodes);
    //得到哈夫曼编码
    Map huffmanCodes = getHuffmanCodes(huffmanTree);

    //压缩
    return zipToByte(contentBytes, huffmanCodes);
  }

  /**
   * 该方法的功能：将字符串对应的byte数组，通过生成的哈夫曼编码表，
   * 返回一个哈夫曼编码压缩后的byte[]
   * 比如：
   * String content="i like like like java do you like a java"
   * byte[] contentBytes = content.getBytes();
   * i(105):101 空格(32):01 l(108):000 ....==> 101 01 000....
   * 通过哈夫曼编码表==>变成 字符串 "1010100010111111110010001011111111001000101111111100100101001101110001110000011011101000111100101000101111111100110001001010011011100"
   * 返回上面字符串对应的byte[] 数组
   * @param contentBytes 原始的字符串对应的byte[]
   * @param huffmanCodes 哈夫曼编码表
   * @return 返回哈夫曼处理后的byte[]
   *
   */
  private static byte[] zipToByte(byte[] contentBytes, Map<Byte, String> huffmanCodes) {
    //利用huffmanCodes将bytes转成哈夫曼编码对应的字符串
    StringBuilder sb = new StringBuilder();
    for (byte b : contentBytes) {
      sb.append(huffmanCodes.get(b));
    }
    //System.out.println(sb.toString());

    //将字符串每8位存放为一个byte

    //创建压缩后的哈夫曼bytes
    int len;
    if (sb.length() % 8 == 0) {
      len = sb.length() / 8;
    } else {
      len = sb.length() / 8 + 1;
    }
    byte[] huffmanCodeZipBytes = new byte[len];
    int index = 0;
    //每8位变成一个byte
    for (int i = 0; i < sb.length(); i += 8) {
      String strByte;
      if (i + 8 > sb.length()) {
        //不够8位
        strByte = sb.substring(i);
      } else {
        strByte = sb.substring(i, i + 8);
      }
      //将strByte转成一个byte，放入huffmanCodeZipBytes中
      huffmanCodeZipBytes[index++] = (byte) Integer.parseInt(strByte, 2);
    }
    return huffmanCodeZipBytes;
  }


  /**
   * 文件对应的
   * 具体哈夫曼编码表
   */
  private static Map<Byte, String> huffmanCodes = new HashMap<>();

  /**
   * 功能：传入一个哈夫曼树，得到该树对应的哈夫曼编码表
   * @param root 哈夫曼树
   * @return 返回对应的哈夫曼编码表
   */
  private static Map getHuffmanCodes(Node root) {
    StringBuilder sb = new StringBuilder();
    if (root == null) {
      return null;
    }
    //处理root的左子树
    getHuffmanCodes(root.left, "0", sb);
    //处理root的右子树
    getHuffmanCodes(root.right, "1", sb);
    return huffmanCodes;
  }

  /**
   * 功能：得到 传入node节点的所有叶子节点的哈夫曼编码
   * 并放入到huffmanCodes集合中
   * @param node 传入节点
   * @param code 路径：左节点：0   右节点：1
   * @param sb 用于拼接路径
   */
  private static void getHuffmanCodes(Node node, String code, StringBuilder sb) {
    StringBuilder sb2 = new StringBuilder(sb);
    sb2.append(code);
    if (node != null) {
      //判断节点是 叶子节点 还是非叶子节点
      //（非叶子节点的data为空）
      if (node.data == null) {
        //非叶子节点
        //向左递归
        getHuffmanCodes(node.left, "0", sb2);
        //向右递归
        getHuffmanCodes(node.right, "1", sb2);
      } else {
        //叶子节点 需要存放起来
        huffmanCodes.put(node.data, sb2.toString());
      }
    }
  }

  /**
   * @param bytes 接收字节数组
   * @return 返回的就是list形式 [ node=[...],node=[...]]
   */
  private static List<Node> getNodes(byte[] bytes) {
    List<Node> list = new ArrayList();
    //用于统计一个byte的出现次数
    Map<Byte, Integer> counts = new HashMap<>();
    for (byte b : bytes) {
      Integer count = counts.get(b);
      if (null == count) {
        //第一次出现设置为1
        counts.put(b, 1);
      } else {
        //第count次出现，自增即可
        counts.put(b, count + 1);
      }
    }
    //将其 转成list
    for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
      list.add(new Node(entry.getKey(), entry.getValue()));
    }

    return list;
  }


  /**
   * 前序遍历的方法
   */
  private static void preOrder(Node root) {
    if (root != null) {
      root.preOrder();
    } else {
      System.out.println("哈夫曼树为空");
    }
  }

  /**
   * 创建一颗哈夫曼树
   * @param nodes 节点集合
   * @return 返回的Node就是一颗哈夫曼树
   */
  private static Node createHuffmanTree(List<Node> nodes) {
    while (nodes.size() > 1) {
      //先排序
      Collections.sort(nodes);

      Node node1 = nodes.get(0);
      Node node2 = nodes.get(1);

      //设置一颗新的二叉树
      Node nodeAdd = new Node(null, node1.weight + node2.weight);
      nodeAdd.left = node1;
      nodeAdd.right = node2;
      //将这颗新的二叉树添加入集合中
      nodes.add(nodeAdd);
      //删除node1、node2
      nodes.remove(node1);
      nodes.remove(node2);
    }
    return nodes.get(0);
  }

}

@Data
@ToString(exclude = {"left", "right"})
class Node implements Comparable<Node> {

  //存放数据（字符）本身，比如 'a' -> 97
  Byte data;
  //权值，表示字符出现的次数
  int weight;

  Node left;
  Node right;

  public Node(Byte data, int weight) {
    this.data = data;
    this.weight = weight;
  }

  @Override
  public int compareTo(Node o) {
    //从小到大排序
    return this.weight - o.weight;
  }

  //前序遍历
  public void preOrder() {
    System.out.println(this);
    if (this.left != null) {
      this.left.preOrder();
    }
    if (this.right != null) {
      this.right.preOrder();
    }
  }
}
