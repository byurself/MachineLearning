package com.lpc.knn;

import java.util.*;

/**
 * @author byu_rself
 * @date 2022/5/15 19:19
 */
public class KNN {

    public static void main(String[] args) {
        // 准备数据（假设参数A为身高，B为体重，C为颜值，用类型分为'帅哥''普通''屌丝'）
        List<KnnModel> knnModelList = new ArrayList<>();
        knnModelList.add(new KnnModel(178, 75, 88, "帅哥"));
        knnModelList.add(new KnnModel(180, 73, 96, "帅哥"));
        knnModelList.add(new KnnModel(183, 80, 95, "帅哥"));
        knnModelList.add(new KnnModel(173, 75, 95, "普通"));
        knnModelList.add(new KnnModel(170, 72, 80, "普通"));
        knnModelList.add(new KnnModel(171, 71, 89, "普通"));
        knnModelList.add(new KnnModel(155, 70, 60, "屌丝"));
        knnModelList.add(new KnnModel(159, 80, 68, "屌丝"));
        knnModelList.add(new KnnModel(160, 75, 70, "屌丝"));
        // 预测数据
        KnnModel model = new KnnModel(176.5, 70, 92, null);
        // 输出预测类型
        System.out.println(calculateKnn(knnModelList, model, 3));
    }

    /**
     * 计算新数据与训练数据的距离
     */
    private static List<KnnModel> calculate(List<KnnModel> modelList, KnnModel model, int k) {
        for (KnnModel m : modelList) {
            double distanceA = Math.abs(model.paramA - m.paramA);
            double distanceB = Math.abs(model.paramB - m.paramB);
            double distanceC = Math.abs(model.paramC - m.paramC);
            // 训练数据保存与目标数据的距离，方便下一步排序
            m.distance = distanceA + distanceB + distanceC;
        }
        // 根据distance大小进行排序(从小到大)
        modelList.sort(Comparator.comparingDouble(KnnModel::getDistance));
        // 返回差距最小的k个值
        List<KnnModel> resultList = new ArrayList<>(k);
        for (int i = 0; i < k; i++) {
            resultList.add(modelList.get(i));
        }
        return resultList;
    }

    /**
     * 统计出最多的类型
     */
    private static String findTypeByScope(List<KnnModel> modelList) {
        Map<String, Integer> typeMap = new HashMap<>(modelList.size());
        // 统计类型
        for (KnnModel model : modelList) {
            int sum = typeMap.get(model.type) == null ? 1 : typeMap.get(model.type) + 1;
            typeMap.put(model.type, sum);
        }
        // 返回出现次数最多的类型
        List<Map.Entry<String, Integer>> list = new ArrayList(typeMap.entrySet());
        list.sort(Comparator.comparingInt(Map.Entry::getValue));
        return list.get(list.size() - 1).getKey();
    }

    /**
     * Knn
     *
     * @param modelList 训练数据集
     * @param model     待分类数据
     * @param k         范围变量
     */
    private static String calculateKnn(List<KnnModel> modelList, KnnModel model, int k) {
        // 1、计算训练数据与待分类数据的各自相对距离，并返回差距最小的K个训练结果
        List<KnnModel> minKnnList = calculate(modelList, model, k);
        // (2) 找出差距最小的K个结果中，最多的类型
        return findTypeByScope(minKnnList);
    }

    // 数据模型
    private static class KnnModel implements Comparable<KnnModel> {
        public double paramA;
        public double paramB;
        public double paramC;
        public double distance;
        String type;

        public double getDistance() {
            return distance;
        }

        public KnnModel(double a, double b, double c, String type) {
            this.paramA = a;
            this.paramB = b;
            this.paramC = c;
            this.type = type;
        }

        @Override
        public int compareTo(KnnModel model) {
            return Double.compare(this.distance, model.distance);
        }
    }
}
