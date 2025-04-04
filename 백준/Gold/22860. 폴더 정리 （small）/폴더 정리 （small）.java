import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 파일 관계를 저장할 맵: 부모 이름 -> FileNode 집합
        Map<String, HashSet<FileNode>> fileMap = new HashMap<>();

        // 첫 줄 입력을 공백 기준으로 나누고, 각 정수를 합산
        String[] parts = sc.nextLine().split(" ");
        int total = 0;
        for (String part : parts) {
            total += Integer.parseInt(part);
        }

        // 파일 관계 입력 처리
        for (int i = 0; i < total; i++) {
            String[] tokens = sc.nextLine().split(" ");
            String parent = tokens[0];
            String child = tokens[1];
            int type = Integer.parseInt(tokens[2]);

            fileMap.computeIfAbsent(parent, k -> new HashSet<>())
                   .add(new FileNode(child, type));
        }

        // 쿼리 개수 처리
        int queries = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < queries; i++) {
            // 경로를 '/' 기준으로 나눈 뒤 마지막 부분을 추출
            String path = sc.nextLine();
            String[] pathParts = path.split("/");
            String last = pathParts[pathParts.length - 1];
            List<String> found = new ArrayList<>();

            // 재귀적으로 탐색
            find(last, fileMap, found);

            // 중복 제거 후 카운트
            Set<String> distinct = new HashSet<>(found);
            System.out.println(distinct.size() + " " + found.size());
        }
        sc.close();
    }

    public static void find(String key, Map<String, HashSet<FileNode>> fileMap, List<String> found) {
        HashSet<FileNode> nodes = fileMap.get(key);
        if (nodes != null) {
            for (FileNode node : nodes) {
                if (node.fileType == 0) {
                    found.add(node.fileName);
                } else {
                    find(node.fileName, fileMap, found);
                }
            }
        }
    }
}

// 파일 노드 정보를 담는 클래스
class FileNode {
    String fileName;
    int fileType;

    public FileNode(String fileName, int fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileNode fileNode = (FileNode) o;
        return fileType == fileNode.fileType && Objects.equals(fileName, fileNode.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, fileType);
    }
}
