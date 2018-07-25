private Set<Path> findSourceFiles(List<Path> sourceRoots) {
        final Pattern extensionPattern = Pattern.compile("([^\\s]+(\\.(?i)(c|cc|cpp)(?<!pb.cc))$)");
        Set<Path> relevantFiles = new HashSet<>();
        sourceRoots.forEach(relativeSourceRoot ->
        {
            try {
                Path sourceRoot = m_workingRoot.resolve(relativeSourceRoot);
                if (!Files.exists(sourceRoot)) {
                    return;
                }

                Files.walk(sourceRoot)
                        .filter(path -> extensionPattern.matcher(path.getFileName().toString()).find())
                        .forEach(path -> relevantFiles.add(path));
            } catch (IOException e) {
            }
        });
        return relevantFiles;
    }
